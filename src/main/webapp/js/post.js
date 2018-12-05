function submitAsPost(url) {
    var postForm = document.createElement('form');
    postForm.action = url;
    postForm.method = 'post';
    var bodyTag = document.getElementsByTagName('body')[0];
    bodyTag.appendChild(postForm);
    postForm.submit();
}