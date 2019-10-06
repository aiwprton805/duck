$(function () {
    bsCustomFileInput.init();
    $('.custom-file input').on('change', function() {
        let inputFileElement = $(this);
        let width = inputFileElement[0].clientWidth / 10 - 2;
        let files = inputFileElement[0].files;

        let label = [].map.call(files,function (file) {
            return file.name;
        }).join(', ');
        if(label.length > width){
            label = label.substring(0, width).concat(' ...');
        }

        inputFileElement.next('.custom-file-label').html(label);
    });
});
