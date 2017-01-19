'use strict';

/**
 * Config for the router
 */
angular.module('app')
  .constant('_', window._)
  .run(
	[          '$rootScope', '$state', '$stateParams',
      function ($rootScope,   $state,   $stateParams) {	  
          $rootScope.$state = $state;
          $rootScope.$stateParams = $stateParams;
		  $rootScope._ = window._; // Lodash
		  //ngWrap('_');
      }
    ]
  )
  .config(
    [          '$stateProvider', '$urlRouterProvider',
      function ($stateProvider,   $urlRouterProvider) {
          
          $urlRouterProvider
              .otherwise('/index');
          $stateProvider
              .state('app', {
                  abstract: true,
                  url: '',
                  templateUrl: 'views/tpl/app.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load(['ui.select']).then(

                          );
                        }
                      ]
                  }
              })
              .state('app.s0', {
                  url: '/index',
                  template: '<div ui-view class="fade-in-up"></div>',
              })
              .state('app.demo', {
                  url: '/demo',
                  template: '<div ui-view class="fade-in-up"></div>'
              })
              .state('app.demo.demo1-list', {
                  url: '/demobanks',
                  templateUrl: 'views/tpl/demo/demobank-list.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load(['ngTable','ui.select']).then(
                              function(){
                                 return $ocLazyLoad.load('views/js/controllers/demo/demobank.js');
                              }
                          );
                        }
                      ]
                  }
              })
              .state('app.demo.demo1-mgmt', {
                  url: '/demobanks/{id}',
                  params: {id:null},
                  templateUrl: 'views/tpl/demo/editDemoBank.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load(['ngTable','ui.select']).then(
                              function(){
                                 return $ocLazyLoad.load('views/js/controllers/demo/demobankEdit.js');
                              }
                          );
                        }
                      ]
                  }
              })
              .state('app.demo.demo2-list', {
                  url: '/demobanks2',
                  templateUrl: 'views/tpl/demo/demobank-list2.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load(['ngTable','ui.select','ngPrint']).then(
                              function(){
                                 return $ocLazyLoad.load('views/js/controllers/demo/demobank2.js');
                              }
                          );
                        }
                      ]
                  }
              }) 
              .state('app.demo.demo3-fileupload', {
                  url: '/fileupload',
                  templateUrl: 'views/tpl/demo/fileupload.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                    	  	return $ocLazyLoad.load(
                    	  			['views/js/controllers/demo/fileupload.js',
                    	  			 'views/js/controllers/common/imageupload.js']);
                        }
                      ]
                  }
              })
              .state('app.demo.demo4-test', {
                  url: '/demo4',
                  templateUrl: 'views/tpl/demo/test.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load(['ngTable']).then(
                              function(){
                                 return $ocLazyLoad.load('views/js/controllers/demo/test.js');
                              }
                          );
                        }
                      ]
                  }
              })    
              .state('app.demo.demo5-pulldown', {
                  url: '/demo5',
                  templateUrl: 'views/tpl/demo/pulldown.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load(['ngTable','ngPrint']).then(
                              function(){
                                 return $ocLazyLoad.load('views/js/controllers/demo/pulldown.js');
                              }
                          );
                        }
                      ]
                  }
              })
              .state('app.demo.demo6-charts', {
                  url: '/demo6',
                  templateUrl: 'views/tpl/demo/charts.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load(['ngPrint']).then(
                              function(){
                                 return $ocLazyLoad.load('views/js/controllers/demo/charts.js');
                              }
                          );
                        }
                      ]
                  }
              })
              
              .state('app.s1', {
                  url: '/master',
                  template: '<div ui-view class="fade-in-up"></div>'
              })
        
              .state('app.s1.company-list', {
                  url: '/companies',
                  templateUrl: 'views/tpl/master/company-list.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load(['ngTable','ui.select']).then(
                              function(){
                                 return $ocLazyLoad.load('views/js/controllers/master/company.js');
                              }
                          );
                        }
                      ]
                  }
              })
              .state('app.s1.company-mgmt', {
                  url: '/companies/{id}',
                  params: {"id":null},
                  templateUrl: 'views/tpl/master/editCompany.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load(['ngTable','ui.select']).then(
                              function(){
                                 return $ocLazyLoad.load('views/js/controllers/master/companyEdit.js');
                              }
                          );
                        }
                      ]
                  }
              })
               .state('app.s1.company-view', {
                  url: '/view',
                  params: {"id":null},
                  templateUrl: 'views/tpl/master/viewCompany.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load(['ngTable','ui.select']).then(
                              function(){
                                 return $ocLazyLoad.load('views/js/controllers/master/companyEdit.js');
                              }
                          );
                        }
                      ]
                  }
              }) 
              .state('app.s1.bank-mgmt', {
                  url: '/banks',
                  templateUrl: 'views/tpl/master/bank-list.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load(['ngTable','ui.select']).then(
                              function(){
                                 return $ocLazyLoad.load('views/js/controllers/master/bank.js');
                              }
                          );
                        }
                      ]
                  }
              })       
              .state('app.s1.account-mgmt', {
                  url: '/bankAccounts',
                  templateUrl: 'views/tpl/master/bankAccount-list.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load(['ngTable','ui.select']).then(
                              function(){
                                 return $ocLazyLoad.load('views/js/controllers/master/bankAccount.js');
                              }
                          );
                        }
                      ]
                  }
              })  
              .state('app.s1.interest-mgmt', {
                  url: '/interests',
                  templateUrl: 'views/tpl/master/interest-list.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load(['ngTable','ui.select']).then(
                              function(){
                                 return $ocLazyLoad.load('views/js/controllers/master/interest.js');
                              }
                          );
                        }
                      ]
                  }
              })
              
               .state('app.s1.sellerBank-mgmt', {
                  url: '/sellerBanks',
                  
                  templateUrl: 'views/tpl/master/editSellerBank.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load(['ngTable','ui.select']).then(
                              function(){
                                 return $ocLazyLoad.load('views/js/controllers/master/editSellerBankController.js');
                              }
                          );
                        }
                      ]
                  }
              })
              //字典管理
                .state('app.s1.dictionary-list', {
                  url: '/syselementgroup',
                  templateUrl: 'views/tpl/master/dictionary-list.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load(['ngTable','ui.select']).then(
                              function(){
                                 return $ocLazyLoad.load('views/js/controllers/master/dictionary.js');
                              }
                          );
                        }
                      ]
                  }
              })
              .state('app.s1.dictionary-mgmt', {
                  url: '/dictionary/{id}',
                  params: {"id":null},
                  templateUrl: 'views/tpl/master/editdictionary.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load(['ngTable','ui.select']).then(
                              function(){
                                 return $ocLazyLoad.load('views/js/controllers/master/dictionaryEdit.js');
                              }
                          );
                        }
                      ]
                  }
              })
//              .state('app.s1.dictionary-create', {
//                  url: '/dictionary/{id}',
//                  params: {"id":null},
//                  templateUrl: 'views/tpl/master/addDictionary.html',
//                  resolve: {
//                      deps: ['$ocLazyLoad',
//                        function( $ocLazyLoad ){
//                          return $ocLazyLoad.load(['ngTable']).then(
//                              function(){
//                                 return $ocLazyLoad.load('views/js/controllers/master/dictionaryEdit.js');
//                              }
//                          );
//                        }
//                      ]
//                  }
//              }) 
               .state('app.s1.dictionary-view', {
                  url: '/dictionaryView/{id}',
                  params: {"id":null},
                  templateUrl: 'views/tpl/master/viewdictionary.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load(['ngTable','ui.select']).then(
                              function(){
                                 return $ocLazyLoad.load('views/js/controllers/master/dictionaryEdit.js');
                              }
                          );
                        }
                      ]
                  }
              }) 
               .state('app.s1.seller-list', {
                  url: '/sellers',
                  templateUrl: 'views/tpl/master/seller-list.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load(['ngTable','ui.select']).then(
                              function(){
                                 return $ocLazyLoad.load('views/js/controllers/master/seller.js');
                              }
                          );
                        }
                      ]
                  }
              })  
              
            .state('app.s1.seller-mgmt', {
                  url: '/sellers/{id}',
                  params: {id:null},
                  templateUrl: 'views/tpl/master/editSeller.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load(['ngTable','ui.select']).then(
                              function(){
                                 return $ocLazyLoad.load(['views/js/controllers/master/editSeller.js',
                                                                   'views/js/controllers/common/imageupload.js']);
                              }
                          );
                        }
                      ]
                  }
              })
              .state('app.s1.seller-view', {
                  url: '/sellerView/{id}',
                  params: {id:null},
                  templateUrl: 'views/tpl/master/viewSeller.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load(['ngTable','ui.select']).then(
                              function(){
                            	  return $ocLazyLoad.load(['views/js/controllers/master/editSeller.js',
                                                           'views/js/controllers/common/imageupload.js']);
                              }
                          );
                        }
                      ]
                  }
              }) 
               .state('app.s1.coderule-mgmt', {
                  url: '/codeRules',
                  templateUrl: 'views/tpl/master/coderule.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load(['ngTable','ui.select']).then(
                              function(){
                                 return $ocLazyLoad.load('views/js/controllers/master/coderule.js');
                              }
                          );
                        }
                      ]
                  }
              })
    
              .state('app.s2', {
                  url: '/system',
                  template: '<div ui-view class="fade-in-up"></div>'
              })
              .state('app.s2.user-list', {
                  url: '/users',
                  templateUrl: 'views/tpl/system/user-list.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load(['ngTable','ui.select']).then(
                              function(){
                                 return $ocLazyLoad.load('views/js/controllers/system/userList.js');
                              }
                          );
                        }
                      ]
                  }
              })
              .state('app.s2.role-list', {
                  url: '/roles',
                  templateUrl: 'views/tpl/system/role-list.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load(['ngTable','ui.select']).then(
                              function(){
                                 return $ocLazyLoad.load('views/js/controllers/system/roleList.js');
                              }
                          );
                        }
                      ]
                  }
              })
              .state('app.s2.auth-list', {
                  url: '/auths',
                  templateUrl: 'views/tpl/system/auth-list.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load(['ngTable','ui.select']).then(
                              function(){
                                 return $ocLazyLoad.load('views/js/controllers/system/authList.js');
                              }
                          );
                        }
                      ]
                  }
              })
               .state('app.s3', {
                  url: '/jobs',
                  template: '<div ui-view class="fade-in-up"></div>'
              })
              .state('app.s3.job-list', {
                  url: '/job-list',
                  templateUrl: 'views/tpl/job/job-list.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load(['ngTable','ui.select']).then(
                              function(){
                                 return $ocLazyLoad.load('views/js/controllers/job/job.js');
                              }
                          );
                        }
                      ]
                  }
              })
              
              // UI components below
              .state('app.ui', {
                  url: '/uicomponent',
                  template: '<div ui-view class="fade-in"></div>',
                  resolve: {
                      deps: ['uiLoad',
                        function( uiLoad){
                          return uiLoad.load('views/js/controllers/form.js');
                      }]
                  }
              })
              .state('app.ui.buttons', {
                  url: '/buttons',
                  templateUrl: 'views/tpl/uitem/ui_buttons.html'
              })
              .state('app.ui.icons', {
                  url: '/icons',
                  templateUrl: 'views/tpl/uitem/ui_icons.html'
              })       
              .state('app.ui.bootstrap', {
                  url: '/bootstrap',
                  templateUrl: 'views/tpl/uitem/ui_bootstrap.html'
              })
              .state('app.ui.tree', {
                  url: '/tree',
                  templateUrl: 'views/tpl/uitem/ui_tree.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('angularBootstrapNavTree').then(
                              function(){
                                 return $ocLazyLoad.load('views/js/controllers/tree.js');
                              }
                          );
                        }
                      ]
                  }
              })
              .state('app.ui.elements', {
                  url: '/elements',
                  templateUrl: 'views/tpl/uitem/form_elements.html'
              })
              .state('app.ui.validation', {
                  url: '/validation',
                  templateUrl: 'views/tpl/uitem/form_validation.html'
              })
              .state('app.ui.fileupload', {
                  url: '/fileupload',
                  templateUrl: 'views/tpl/uitem/form_fileupload.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad){
                          return $ocLazyLoad.load('angularFileUpload').then(
                              function(){
                                 return $ocLazyLoad.load('views/js/controllers/file-upload.js');
                              }
                          );
                      }]
                  }
              })

      }
    ]
  );