// Generated by CoffeeScript 1.7.1
(function(window, angular) {

  /*
  angualr 指令
  手指轻碰一下就会触发事件
   */
  'use strict';
  var IsTouch, LOC, NA, UA, WIN, tap;
  tap = angular.module('binnng.tap', []);
  WIN = window;
  IsTouch = "ontouchend" in WIN;
  if (!IsTouch) {
    return false;
  }
  LOC = location;
  NA = WIN.navigator;
  UA = NA.userAgent;
  return tap.directive("tap", function() {
    return {
      link: function(scope, element, attrs) {
        var fn, fnName;
        fnName = attrs["tap"].replace("()", "");
        fn = scope[fnName];
        if (!fn) {
          return false;
        }
        return element.on("touchstart", function(event) {
          fn();
          return event.stopPropagation();
        });
      }
    };
  });
})(window, angular);
