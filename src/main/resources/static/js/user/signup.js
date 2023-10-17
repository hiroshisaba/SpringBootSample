'use strict'

/** 画面ロード時の処理 */
jQuery(function($){
  /** 登録ボタンを押した時の処理 */
  $('#btn-signup').click(function (event){

    // ユーザー登録
    signupUser();
  });


});

function signupUser() {

  removeValidResult();
  let formData = $('#signup-form').serializeArray();

  // ajax通信
  $.ajax({
    type: "POST",
    cache: false,
    url: '/user/signup/rest',
    data: formData,
    dataType: 'json'
  }).done(function(data){
    console.log(data);

    if(data.result===90) {
      $.each(data.errors, function(key, value) {
        reflectValidResult(key, value);
      });
    } else if(data.result === 0) {
      alert('ユーザーを登録しました');
      window.location.href = '/login';
    }
  }).fail(function(jqXHR, textStatus, errorThrown){
    alert('ユーザー登録に失敗しました');
  }).always(function(){
    // 常に実行する処理
  });
}

/** バリデーション結果をクリア */
function removeValidResult() {
  $('.is-invalid').removeClass('is-invalid');
  $('.invalid-feedback').remove();
  $('.text-danger').remove();
}

function reflectValidResult(key, value) {
  if(key === 'gender'){
    $('input[name=' + key + ']').addClass('is-invalid');
    // エラーメッセージ追加
    $('input[name=' + key + ']').parent().parent().append('<div class="text-danger">' + value + '</div>');
  } else {
    $('input[id=' + key + ']').addClass('is-invalid');
    // エラーメッセージ追加
    $('input[id=' + key + ']').after('<div class="invalid-feedback">' + value + '</div>');

  }

}