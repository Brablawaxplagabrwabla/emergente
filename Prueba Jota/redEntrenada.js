const { NeuralNetwork } = require('brain.js');
const _ = require('lodash');
const fs = require('fs');

const raw = fs.readFileSync('./Data.csv', 'utf8').split('\n');
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


    if (headers[i].includes('shoulderlength') || headers[i].includes('waistcircumference') || headers[i].includes('Weightlbs')){
      cur[headers[i]] = parseFloat(v) / 1000;
    } else if (headers[i].includes('Age') || headers[i].includes('Heightin') ) {
      cur[headers[i]] = parseFloat(v) / 100;
    } else {
      cur[headers[i]] = parseFloat(v) / 1;
    }
    return cur;
  }, {}));


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

// -----------------------------------------------------------------------------------------------
const net2 = new NeuralNetwork();
net2.fromJSON(JSON.parse(fs.readFileSync('./net.json', 'utf8')));

let error = 0;
for (let i = 0; i < 2400; ++i) {
  const { Gender } = net2.run(_.omit(data[numTrainingData + i], ['Gender']));
  error += Math.abs(Gender - data[numTrainingData + i].Gender);
  console.log(i, Gender, data[numTrainingData + i].Gender);
}

console.log('Error absoluto medio', error / 2400);
console.log('Fin');


// ------------------------------------------------------------------------------------------------

// Probar aca muchachos 
var output = net2.run({ shoulderlength: 0.141, waistcircumference: 0.1046, Age: 0.17, Heightin: 0.70, Weightlbs: 0.182 });  // Hombre ?
var gender_predicted = (output.Gender <= 0.5) ? "Masculino" : "Femenino" ;
console.log("Genero : ", output );
console.log("La persona es de género "+gender_predicted+" con un error medio de  "+ error / 2400);