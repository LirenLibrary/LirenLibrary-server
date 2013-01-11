angular.module('commonFilters', []).filter('timestamp', function($filter) {
  return function(timestamp, pattern) {
    return $filter('date')(new Date(timestamp), pattern);
  };
});