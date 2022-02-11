$( document ).ready(function() {

});
var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            //_this.save();
            _this.saveResult();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });

        $('#resultTitle').on('change', function(){
            _this.select();
        })
    },
    select: function() {
        var data = {
            title : $('#resultTitle option:selected').text()
        };
        $.ajax({
                    type: 'GET',
                    url: '/api/v1/rank/' + data.title,
                    dataType: 'json',
                    contentType:'application/json; charset=utf-8',
                }).done(function(data) {
                    var result = data;
                    var template = '{{#result}}<tr><th scope="row">{{rank}}</th> <td>{{driver}}</td> <td>{{point}}</td></tr>{{/result}}';
                    Mustache.parse(template);
                    var rendered = Mustache.render(template, result);
                    $('#resultTbody').html(rendered);
                }).fail(function (error) {
                    alert(JSON.stringify(error));
                });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
     saveResult : function () {
            var score_cal =[1,25,18,15,12,10,8,6,4,2,1,0,0,0,0,0,0,0,0,0,0]
            console.log(score_cal.length);
            var dataArray = [];
            var title;
            var raceDate;
            var circuit;
            var ranking;
            var driver;
            var notes;
            var point;
            var inputData ={};
            var fastestLapPoint = 0;
            var dnf;
            var dns;
            for(var i=1; i<= 20; i++){
                if($('#driver_'+i).val() == "" || $('#driver_'+i).val() == null){
                     continue;//드라이버가 없으면 continue
                     dns = 0;
                }else{
                     dns = 1;
                }
                if($('#notes_'+i).val() == "FastestLap"){
                    fastestLapPoint =1;
                }else{
                    fastestLapPoint=0;
                }
                if($('#notes_'+i).val() == "DNF"){
                    dnf = 0;
                }else{
                    dnf = 1;
                }

                inputData = {
                   title : $('#title').val(),
                   raceDate : $('#date-picker1').val(),
                   circuit : $('#circuit').val(),
                   ranking : $('#ranking_'+i).val(),
                   driver : $('#driver_'+i).val(),
                   notes : $('#notes_'+i).val(),
                   point : (score_cal[i] + fastestLapPoint) * dnf * dns
                };
                dataArray.push(inputData);
            }
            $.ajax({
                type: 'POST',
                url: '/api/v1/resultAll',
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify(dataArray)
            }).done(function() {
                alert('저장완료');
                window.location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }

};

main.init();