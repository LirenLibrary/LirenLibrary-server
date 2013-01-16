angular.module('commonFilters', []).filter('unixTimestamp', function($filter) {
  return function(timestamp, pattern) {
    return $filter('date')(new Date(timestamp * 1000), pattern);
  };
});