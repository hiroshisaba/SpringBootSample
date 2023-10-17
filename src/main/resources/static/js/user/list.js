'use strict';

let userData = null;
let table = null;

jQuery(function($){

  createDataTables();

  $('#btn-search').click(function(event){
    search();
  });
});

function search() {
  let formData = $('#user-search-form').serialize();

  $.ajax({
    type: "GET",
    url: '/user/get/list',
    data: formData,
    dataType: 'json',
    contentType: 'application/json; charset=UTF-8',
    cache: false,
    timeout: 5000
  }).done(function(data){
    console.log(data);
    userData=data;
    createDataTables();
  }).fail(function(jqXHR, textStatus, errorThrown){
    alert('検索処理に失敗しました');
  }).always(function(){

  });

}

function createDataTables() {
  if(table !== null) {
    table.destroy();
  }

  table = $('#user-list-table').DataTable({
    language: {
      url:'/webjars/datatables-plugins/i18n/Japanese.json'//1.10.21/
      //url:'/webjars/datatables-plugins/i18n/Japanese.json'
    },
    data: userData,
    columns:[
      {data: 'userId'},
      {data: 'userName'},
      {
        data: 'birthday',
        render: function(data, type, row) {
          let date = new Date(data);
          let year = date.getFullYear();
          let month = date.getMonth() + 1;
          date = date.getDate();
          return year + '/' + month + '/' + date;
        }
      },
      {data: 'age'},
      {
        data: 'gender',
        render: function(data, type, row) {
          let gender = '';
          if(data === 1) {
            gender = '男性';
          } else {
            gender = '女性';
          }
          return gender;
        }
      },
      {
        data: 'userId',
        render: function(data, type, row) {
          let url = '<a href="/user/detail/' + data + '">詳細</a>';
          return url;
        }
      }
    ]

  });
}
