var app = new Vue({
    el: "#app",
    data: {

        user:{
            "id":1,
            "file":10,
            "dni":28165509
             },
        subjects:[],
        subject:{
               "name":"",
               "time":"",
               "teacher":null,
               "availability":null,
                },
        subjectId:null,
        subjectName:"",
        subjectTime:null,
        subjectAvailability:null,
        subjectTeacher:null,
        deleteBySubjectId:null,
        teacherId:null,
        teacher:{
                "name":"",
                "lastName":"",
                "dni":null,
                "active":false
                 },
        teacherName:"",
        teacherLastName:"",
        teacherDni:null,
        teacherActive:false,
        deleteTeacherId:null,
    },
    methods: {

        //teachers

        createTeacher: function (){
             app.teacher.name = app.teacherName;
             app.teacher.lastName = app.teacherLastName;
             app.teacher.dni = app.teacherDni;
             app.teacher.active = app.teacherActive;
            $.post({
                url: "/api/teachers",
                data: JSON.stringify(app.teacher),
                dataType: "text",
                contentType: "application/json"
            })
            .done(function (response) {
                var newResponse = JSON.parse(response);
                alert("Teacher Created");
                app.teacherName = "";
                app.teacherLastName = "";
                app.teacherDni = null;
                app.teacherActive = false;
            })
            .fail(function (error) {
                var newError =  JSON.parse( error.responseText);
                alert(newError.error);
            })
        },

        updateTeacher: function(){
             app.teacher.name = app.teacherName;
             app.teacher.lastName = app.teacherLastName;
             app.teacher.dni = app.teacherDni;
             app.teacher.active = app.teacherActive;
            $.ajax({
                url: "/api/teachers/"+app.teacherId,
                type: "put",
                data: JSON.stringify(app.teacher),
                dataType: "text",
                contentType: "application/json"
            })
            .done(function (response) {
                var newResponse = JSON.parse(response);
                alert("Teacher Updated");
                app.teacherName = "";
                app.teacherLastName = "";
                app.teacherDni = null;
                app.teacherActive = false;

            })
            .fail(function (error) {
                var newError =  JSON.parse( error.responseText);
                alert(newError.error);
            })
        },

        deleteTeacher: function(){
            $.ajax({
                 url: "/api/teachers/"+app.deleteTeacherId,
                 type: "DELETE",
            })
            .done(function (response) {
                alert("Teacher Deleted");
                app.teacherId = null;
            })
            .fail(function (error) {
                var newError =  JSON.parse( error.responseText);
                alert(newError.error);
            })
        },

        //subjects

        createSubject: function (){
            app.subject.name = app.subjectName;
            app.subject.time = app.subjectTime;
            app.subject.availability = app.subjectAvailability;
            $.post({
                url: "/api/subjects/"+app.subjectTeacher,
                data: JSON.stringify(app.subject),
                dataType: "text",
                contentType: "application/json"
            })
            .done(function (response) {
                alert("Subject Created");
                location.reload();
            })
            .fail(function (error) {
                var newError =  JSON.parse( error.responseText);
                alert(newError.error);
            })
        },

        updateSubject: function(){
            app.subject.name = app.subjectName;
            app.subject.time = app.subjectTime;
            app.subject.availability = app.subjectAvailability;
            $.ajax({
                url: "/api/subjects/"+app.subjectId+"/"+app.subjectTeacher,
                type: "put",
                data: JSON.stringify(app.subject),
                dataType: "text",
                contentType: "application/json"
            })
            .done(function (response) {
                var newResponse = JSON.parse(response);
                alert("Subject Updated");
                location.reload();
            })
            .fail(function (error) {
                var newError =  JSON.parse( error.responseText);
                alert(newError.error);
            })
        },

        deleteSubject: function(){
            $.ajax({
                 url: "/api/subjects/"+app.deleteBySubjectId,
                 type: "DELETE",
            })
            .done(function (response) {
                alert("Subject Deleted");
                location.reload();
            })
            .fail(function (error) {
                var newError =  JSON.parse( error.responseText);
                alert(newError.error);
            })
        },

        joinSubject: function (id){
            $.post({
                url: "/api/subscriptions/1/1",
                data: JSON.stringify(app.subscription),
                dataType: "text",
                contentType: "application/json"
            })
            .done(function (response) {
                alert("Successful Subscription");

            })
            .fail(function (error) {
                var newError =  JSON.parse( error.responseText);
                alert(newError.error);
            })
        },
    }
});

var api ='/api/subjects';

fetch(api, {
    method: 'GET',
    headers: {
    }
}).then(function (response) {
    console.log(response)
    if (response.ok) {
        return response.json();
    }
    else {
        throw new Error(response.status)
    }
})
    .then(function (data) {
    app.subjects = data;
    })
    .catch(function (error) {
        console.log('Looks like there was a problem: \n', error);
    });