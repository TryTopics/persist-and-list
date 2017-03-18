const request = require('superagent');

const solr = {
  //host: 'http://solr:8983'
  host: 'http://192.168.99.112'
};

module.exports = function(clientReq, clientRes) {

  request
    .get(solr.host + '/solr/repositem/select')
    .query({'q': '*:*'})
    .query({'fq': 'pathext:xml'})
    .query({'sort': 'revt desc,name asc'})
    .query({'wt': 'json'})
    .query({'rows': 100})
    .set('Accept', 'application/json')
    .end(function(err, res){
      if (err) {
        throw new Error('Argh! ' + err);
      }
      var list = res.body;
      clientRes.send(list);
    });

};
