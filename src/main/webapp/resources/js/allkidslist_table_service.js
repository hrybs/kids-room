'use strict';

function AllKidsTableService($http, $q) {

    return ({
        getActiveChildren: getActiveChildren,
        getAllChildren: getAllChildren,
        addChild: addChild,
        searchChildren: searchChildren,
        searchParents: searchParents,
        getParent: getParent,
        getLocale: getLocale
    });

    function getActiveChildren(roomId) {

        var request = $http({
            method: 'get',
            url: 'api/room/' + roomId + '/children',
            params: {
                action: 'get'
            }
        });

        return ( request.then(handleSuccess, handleError) );
    }

    function getAllChildren() {

        var request = $http({
            method: 'get',
            url: 'api/child',
            params: {
                action: 'get'
            }
        });

        return ( request.then(handleSuccess, handleError) );
    }

    function addChild(child) {

        var request = $http.post('api/child', child);

        return ( request.then(handleSuccess, handleError) );

    }

    function searchChildren(field) {

        var request = $http({
            method: 'get',
            url: 'api/child/search',
            params: {
                action: 'get',
                field: field
            }
        });

        return ( request.then(handleSuccess, handleError) );
    }

    function getParent(childId) {

        var request = $http({
            method: 'get',
            url: 'api/child/' + childId + '/parent',
            params: {
                action: 'get'
            }
        });

        return ( request.then(handleSuccess, handleError) );
    }

    function searchParents(field) {

        var request = $http({
            method: 'get',
            url: 'api/user/search',
            params: {
                action: 'get',
                field: field
            }
        });

        return ( request.then(handleSuccess, handleError) );
    }

    function getLocale(locale) {

        var request = $http({
            method: 'get',
            url: 'api/localization/' + locale,
            params: {
                action: 'get'
            }
        });

        return ( request.then(handleSuccess, handleError) );
    }

    function handleError(response) {

        if (!(!angular.isObject(response.data) || !response.data.message)) {
        } else {
            return ( $q.reject('An unknown error occurred.') );
        }

        return ( $q.reject(response.data.message) );
    }

    function handleSuccess(response) {
        return ( response.data );
    }
}
