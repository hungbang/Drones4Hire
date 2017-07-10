'use strict';

DronesAdmin.controller('UsersPageController', [ '$scope', '$http', '$route', '$location', 'PAGE_SIZES', function($scope, $http, $route, $location, PAGE_SIZES) {
	
	$scope.isPilot = function() {
		return $route.current.loadedTemplateUrl.endsWith('pilots');
	};
	
	var DEFAULT_USER_SEARCH_CRITERIA = {
			'pageSize' : PAGE_SIZES[0],
			'role' : ($scope.isPilot() ? "ROLE_PILOT" : "ROLE_CLIENT"),
			'licenseVerified' : $location.search().licenseVerified
	};
	
	$scope.sc = angular.copy(DEFAULT_USER_SEARCH_CRITERIA);
	$scope.pageSizes = PAGE_SIZES;
	$scope.title = $scope.isPilot() ? "Pilots" : "Clients";
	
	$scope.searchUsers = function(page, pageSize)
	{
		$scope.sc.page = page;
		if(pageSize) $scope.sc.pageSize = pageSize;
		$http.post('users/search', $scope.sc).success(function(data) 
		{
			$scope.sr = data;
			$scope.sc.page = data.page;
			$scope.sc.pageSize = data.pageSize;
		}).error(function() {
			console.error('Failed to search users');
		});
	};
	

	$scope.resetSearchCriteria = function() {
		$scope.sc = angular.copy(DEFAULT_USER_SEARCH_CRITERIA);
		$scope.searchUsers(1);
	};

    $scope.downloadCSV = function(callback){
        var result;
        $http.post('users/csv', $scope.sc).success(function(csv) {
            var blob = new Blob([csv], { type: 'text/csv' });
            var csvUrl = URL.createObjectURL(blob);
            angular.element("<a/>").attr({
                'download': $scope.sc.role.toLowerCase() + '.csv',
                'href': csvUrl
            })[0].click();
            result = true;
        }).error(function() {
            console.error('Failed to download CSV');
            result = false;
        }).finally(function () {
            if (callback) {
                callback(result);
            }
        });
    };
		
	(function init() {
		$scope.searchUsers(1);
	})();
	
}]);

DronesAdmin.controller('UserDetailsController', [ '$scope', '$http', '$location', '$routeParams', '$modal', '$route', '$upload', function($scope, $http, $location, $routeParams, $modal, $route, $upload) {
	
	$scope.tabs = [{ active: true }, { active: false }, { active: false }, { active: false }, { active: false }, { active: false }];
	
	$scope.message = {};
	$scope.messages = [];
	
	$scope.loadLocationsData = function() {
		$http.get('locations/states/list').success(function(data) {
			$scope.listSatesResult = data;
		}).error(function(data, status) {
			alert('Failed to load states');
		});
		$http.get('locations/countries/list').success(function(data) {
			$scope.listCountriesResult = data;
		}).error(function(data, status) {
			alertify.error('Failed to load countries');
		});
	};
	
	$scope.loadUser = function() {
		$http.get('users/' + $routeParams.id).success(function(data) {
			$scope.user = data;
		}).error(function(data, status) {
			alertify.error('Failed to load user');
		});
	};

    $scope.loadFeedbacks = function() {
        $http.get('users/' + $routeParams.id + '/feedbacks').success(function(data) {
            $scope.feedbacks = data;
            $scope.feedbacks.filter(function (feedback) {
            	var intNum = parseInt(feedback.mark);
				if(intNum != feedback.mark)
				{
					feedback.halfStar = true;
				}
                feedback.stars = [];
				for(var i = 0; i < intNum; i++)
				{
					feedback.stars.push(i);
				}
            })
        }).error(function(data, status) {
            alertify.error('Failed to load feedbacks');
        });
    };
	
	$scope.loadLicense = function() {
		$http.get('users/' + $routeParams.id + '/license').success(function(data) {
			$scope.license = data;
		}).error(function(data, status) {
			alertify.error('Failed to load license');
		});
	};
	
	$scope.loadMessages = function() {
		$http.get('users/' + $routeParams.id + '/messages').success(function(data) {
			$scope.messages = data;
		}).error(function(data, status) {
			alertify.error('Failed to load messages');
		});
	};
	
	$scope.loadCompanyData = function() {
		$http.get('users/' + $routeParams.id + '/companies').success(function(data) {
			$scope.company = data;
		}).error(function(data, status) {
			alertify.error('Failed to load company');
		});
	};
	
	$scope.loadSettingsData = function() {
		$http.get('users/' + $routeParams.id + '/notifications').success(function(data) {
			$scope.settings = data;
		}).error(function(data, status) {
			alertify.error('Failed to load notification settings');
		});
	};
	
	(function init(){
		$scope.loadLocationsData();
		$scope.loadUser();
		$scope.loadFeedbacks();
		$scope.loadLicense();
		$scope.loadMessages();
		$scope.loadCompanyData();
		$scope.loadSettingsData();
	})();

    $scope.deleteFeedback = function(id) {
        $http.delete('users/feedbacks/' + id).success(function(data) {
            $route.reload();
        }).error(function(data, status) {
            alertify.error('Failed to delete feedback');
        });
    };
	
	$scope.sendMessage = function(message){
		$scope.message.type = 'EMAIL';
		$http.post('users/' + $routeParams.id + '/messages', message).success(function(data) {
			$scope.message = {};
			$scope.messages.push(data);
			alertify.success('Message sent successfully');
		}).error(function(data, status) {
			alertify.error('Failed to send message');
		});
	};
	
	$scope.editUser = function(id){
		$http.put('users/' + id, $scope.user).success(function(data) {
			alertify.success('Changes saved successfully');
		}).error(function(data, status) {
			alertify.error('Failed to save changes');
		});
	};
	
	$scope.editLocation = function(id) {
		$http.put('locations/' + id, $scope.user.location).success(function(data) {
			alertify.success('Changes saved successfully');
		}).error(function(data, status) {
			alertify.error('Failed to save changes');
		});
	};
	
	$scope.editCompany = function(userId) {
		$http.put('users/' + userId + "/companies", $scope.company).success(function(data) {
			alertify.success('Changes saved successfully');
		}).error(function(data, status) {
			alertify.error('Failed to save changes');
		});
	};
	
	$scope.editSettings = function(userId) {
		$http.put('users/' + userId + "/notifications", $scope.settings).success(function(data) {
			alertify.success('Changes saved successfully');
		}).error(function(data, status) {
			alertify.error('Failed to save changes');
		});
	};
	
	$scope.editLicense = function(license) {
		$http.put('users/' + $routeParams.id + "/licenses", license).success(function(data) {
			alertify.success('Changes saved successfully');
		}).error(function(data, status) {
			alertify.error('Failed to save changes');
		});
	};

    $scope.openFeedbackModal = function (feedback) {
        var modalInstance = $modal.open({
            templateUrl: 'resources/templates/modal/feedback.html',
            scope: $scope,
            resolve: {
                'feedback': function () {
                    return feedback;
                }
            },
            controller: function ($scope, $modalInstance) {

                $scope.feedback = feedback;

                $scope.updateFeedback = function(feedback){
                    $http.put('users/feedbacks', feedback).success(function(data) {
                        alertify.success('Feedback successfully updated');
                        $scope.cancel();
                    }).error(function() {
                        alertify.error('Failed to update feedback');
                    });
                };

                $scope.cancel = function () {
                    $modalInstance.close(false);
                };

                (function init() {
                })();
            }
        }).result.then(function () {
        }, function () {
        });
    };
	
	$scope.contactUser = function(userId){
		$http.get('users/' + userId + '/contact/messages').success(function(data) {
			$modal.open({
				templateUrl : 'resources/templates/users/contactUserModal.jsp',
				resolve : {
					'messages' : function(){
						return data;
					},
					'userId' : function(){
						return userId;
					}
				},
				controller : function($scope, $modalInstance, messages, userId){
					$scope.messages = messages;
					$scope.supportMessage = {};
					
					$scope.sendEmail = function() {
						$scope.supportMessage.userId = userId;
						$scope.supportMessage.type = 'EMAIL';
						
						$http.post('users/contact', $scope.supportMessage).success(function(data) {
						}).error(function() {
							alert('Failed to send Email');
						});
						
						$modalInstance.close(0);
					};
					
					$scope.close = function(){
						$modalInstance.close(0);
					};
				}
			});
		}).error(function() {
			console.error('Failed to load user contact messages');
		});
	};
	
}]);
