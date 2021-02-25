 const path = require('path');

 module.exports = {
   entry: {
     app: './src/main/resources/js/App.js'
   },
   output: {
      path: __dirname,
      filename: './src/main/resources/static/built/bundle.js'
   },

   module: {
     rules: [{
       test: /\.js$/, // include .js files
       enforce: "pre", // preload the jshint loader
       exclude: /node_modules/, // exclude any and all files in the node_modules folder
       use: [{
                                  loader: 'babel-loader',
                                  options: {
                                      presets: ["@babel/preset-env", "@babel/preset-react"]
                                  }
                              }]
     },
     {
             test: /\.css$/i,
             use: ['style-loader', 'css-loader'],
     }]
   },
 };