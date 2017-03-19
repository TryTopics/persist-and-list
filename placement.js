
const short = require('short-uuid');

const caseInsensitivityAlphabet = '0123456789abcdefghjkmnpqrstvwxyz'; // https://en.wikipedia.org/wiki/Base32#Crockford.27s_Base32
const naming = short(caseInsensitivityAlphabet);

module.exports = function Placement() {

  this.shard = function(id) {
    return return id.substr(0,2) + '/' + id.substr(2,2) + '/' + id;
  };

  this.generate = function() {
    return naming.new();
  };
  
  return this;

};
