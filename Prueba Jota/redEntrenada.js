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

let errorAbs = 0;
let errorCudratico = 0;
for (let i = 0; i < 2426; ++i) {
  const { Gender } = net2.run(_.omit(data[numTrainingData + i], ['Gender']));
  errorCudratico += Math.pow((Gender - data[numTrainingData + i].Gender),2)
  errorAbs += Math.abs(Gender - data[numTrainingData + i].Gender);
  console.log(i, Gender, data[numTrainingData + i].Gender,  data[numTrainingData + i].waistcircumference );
  if(i >= 2424) {
    console.log(i, Gender, data[numTrainingData + i].Gender,  data[numTrainingData + i].waistcircumference );
    let gender_predicted = (Gender <= 0.5) ? "Masculino" : "Femenino" ;
    console.log("La persona es de género: "+gender_predicted);
  }
  

  
}

//var output = net2.run({ shoulderlength: 0.135, waistcircumference: 0.1077, Age: 0.39, Heightin: 0.66, Weightlbs: 0.195 });  // Hombre ?
console.log('Error absoluto medio: ', errorAbs / 2426);
console.log('Error cuadático medio: ', errorCudratico/2426);
console.log('Fin');


// ------------------------------------------------------------------------------------------------

// Probar aca muchachos 

//console.log("La persona es de género "+gender_predicted+" con un error absoluto medio de  "+ errorAbs / 2428);