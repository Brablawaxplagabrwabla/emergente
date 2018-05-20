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
let error2 = 0;
for (let i = 0; i < 2426; ++i) {
  const { Gender } = net2.run(_.omit(data[numTrainingData + i], ['Gender']));
  error += Math.abs(Gender - data[numTrainingData + i].Gender);
  error2 += Math.pow((Gender - data[numTrainingData + i].Gender), 2);
  console.log(i, Gender, data[numTrainingData + i].Gender);
}

console.log('Error absoluto medio', error / 2426);
console.log('Error cuad´ratico medio', error2 / 2426);
console.log('Fin');


// ------------------------------------------------------------------------------------------------

// Probar aca muchachos 
var output = net2.run({ shoulderlength: 0.152, waistcircumference: 0.1080, Age: 0.38, Heightin: 0.73, Weightlbs: 0.218 });  // Hombre ?
var gender_predicted = (output.Gender <= 0.5) ? "Masculino" : "Femenino" ;
console.log("Genero : ", output );
console.log("La persona es de género "+gender_predicted+" con un error medio de  "+ error / 2427);
console.log("Lo que tiene la ultima tupla segun el csv es : ", data[6066]);