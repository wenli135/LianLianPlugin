var exec = cordova.require('cordova/exec');
var LianLianPay = function () {
}

LianLianPay.prototype.startPay = function(gatewayUrl, successCallback, errorCallback) {
    exec(successCallback, errorCallback, 'LianPayPlugin', 'startPay', [gatewayUrl]);
}

LianLianPay.prototype.show = function(gatewayUrl, successCallback, errorCallback) {
    exec(successCallback, errorCallback, 'LianPayPlugin', 'show', [gatewayUrl]);
}

if (typeof module != 'undefined' && module.exports) {
    module.exports = LianLianPay;
}
