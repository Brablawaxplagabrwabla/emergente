const { NeuralNetwork } = require('brain.js');
const _ = require('lodash');
const fs = require('fs');

const raw = fs.readFileSync('./DataCorregida.csv', 'utf8').split('\n');
const headers = raw[0].split(',').map(header => header.replace(/'/g, ''));

// Lectura
const data = raw.
  slice(1).
  map(line => line.split(',').
  reduce((cur, v, i) => {

    /** 
     * @author Jota
     * Cambiar escala  0 y 1
     */

    // TODO: Al parecer hay circunferencias mayores a 1000, implica dividir entre 10000.
    // TODO: Hay datos tóxicos en la data, purgar todos los ceros o incongruencia que destruyen la red.
    if (headers[i].includes('shoulderlength') || headers[i].includes('Weightlbs')){
      cur[headers[i]] = parseFloat(v) / 1000;
    } else if ( headers[i].includes('waistcircumference')) {
      cur[headers[i]] = parseFloat(v) / 10000;
    } else if (headers[i].includes('Age') || headers[i].includes('Heightin') ) {
      cur[headers[i]] = parseFloat(v) / 100;
    } else {
      cur[headers[i]] = parseFloat(v) / 1;
    }
    return cur;
  }, {}));

const net = new NeuralNetwork();
const numTrainingData = 3641; // 60% de nuestra data

console.log("Así queda el csv: ", data);
console.log("----------------------------------------------------------------------------------------");

const trainingData = data.
  slice(0, numTrainingData).
  map(obj => ({
    input: _.omit(obj, ['Gender']),
    output: _.pick(obj, ['Gender'])
  }));

console.log(trainingData[0]);

console.log('Entrenamiento finalizado', net.train(trainingData));

// -----------------------------------------------------------------------------------------------
let error = 0;
for (let i = 0; i < 2400; ++i) {
  const { Gender } = net.run(_.omit(data[numTrainingData + i], ['Gender']));
  error += Math.abs(Gender - data[numTrainingData + i].Gender);
  console.log(i, Gender, data[numTrainingData + i].Gender);
}

console.log('Error absoluto medio', error / 2400);

console.log('Fin');

// ------------------------------------------------------------------------------------------------

// Serializa la estructura para usar despes
fs.writeFileSync('./net.json', JSON.stringify(net.toJSON(), null, '  '));
