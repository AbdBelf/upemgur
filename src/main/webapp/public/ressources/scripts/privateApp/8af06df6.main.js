!function(){window.App=Ember.Application.create()}(),function(){App.ClientImageController=Ember.Controller.extend({actions:{deleteImage:function(){model=this.get("content"),self=this,$.ajax({url:WS_HOST+"/upemgur/deleteImage/"+model.id,type:"GET"}).then(function(){window.location="http://localhost:8080/upemgur/index"},function(){self.send("openErrorPopup","Error","Server not responding, Please try later...")})},shareImage:function(){model=this.get("content"),share=!0,1==model.publicImage&&(share=!1),self=this,$.ajax({url:WS_HOST+"/upemgur/shareImage/"+model.id+"?share="+share,type:"GET"}).then(function(){share?(model.publicImage=1,self.set("isPublic",!0),self.send("openErrorPopup","Public Image","Picture profile : Public")):(model.publicImage=0,self.set("isPublic",!1),self.send("openErrorPopup","Private Image","Picture profile : Private"))},function(){self.send("openErrorPopup","Error","Server not responding, Please try later...")})}}})}(),function(){App.AdminImageController=App.ClientImageController.extend({})}(),function(){App.ApplicationController=Ember.ArrayController.extend({})}(),function(){App.ClientImagesController=Ember.ObjectController.extend({actions:{uploadImage:function(){var a=new FormData,b=this;return filesUpload=$("#inputFile").prop("files"),void 0==filesUpload[0]||"image/jpeg"!=filesUpload[0].type?void alert("Error , please choose JPEG format only."):(a.append("files",filesUpload[0]),a.append("fileName",filesUpload[0].name),void $.ajax({url:WS_HOST+"/upemgur/uploadImage",type:"POST",processData:!1,contentType:!1,data:a,headers:{"X-CSRF-Token":$("#X-CSRF-Token").val()}}).then(function(){window.location="http://localhost:8080/upemgur/index"},function(){b.send("openErrorPopup","Error","Server not responding, Please try later...")}))}}}),Ember.Handlebars.helper("getImageName",function(a){return a.replace(/^.*[\\\/]/,"")})}(),function(){App.CreateUserController=Ember.Controller.extend({email:"",password:"",actions:{createUser:function(){this.email=$("#createUserEmail").val(),this.password=$("#createUserPassword").val(),self=this,$.ajax({url:WS_HOST+"/upemgur/createUser",type:"POST",dataType:"json",data:{email:self.email,password:self.password},headers:{"X-CSRF-Token":$("#X-CSRF-Token").val()}}).then(function(){self.send("openErrorPopup","Succes !","User created successfully ..."),$("#createUserEmail").val(""),$("#createUserPassword").val("")},function(){self.send("openErrorPopup","Error","Server not responding, Please try later...")})}}})}(),function(){App.ModalController=Ember.Controller.extend({header:"",content:""})}(),function(){App.ApplicationAdapter=DS.FixtureAdapter}(),function(){App.AdminImageRoute=Ember.Route.extend({model:function(a){return a.image},setupController:function(a,b){a.set("model",b),a.set("imageUrl",b.url),a.set("imageExif",b.exif),1==b.publicImage?a.set("isPublic",!0):a.set("isPublic",!1),$.ajax({url:WS_HOST+"/public/upemgur/downloadImage/"+b.id,type:"GET"}).then(function(b){a.set("base64","data:image/jpg;base64,"+b)},function(){self.send("openErrorPopup","Error","Server not responding, Please try later...")})}})}(),function(){App.AdminImagesRoute=Ember.Route.extend({model:function(){return $.ajax({url:WS_HOST+"/upemgur/getAllImages",type:"GET",contentType:"application/json"}).then(function(a){return a},function(){window.location="http://localhost:8080/login"})},setupController:function(a,b){a.set("model",b.content)}})}(),function(){App.ApplicationRoute=Ember.Route.extend({actions:{openErrorPopup:function(a,b){this.controllerFor("modal").set("header",a),this.controllerFor("modal").set("content",b),this.render("modal",{into:"application",outlet:"modal",view:"modal"})},closeErrorPopup:function(){this.disconnectOutlet({outlet:"modal",parentView:"application"})}}})}(),function(){App.ClientImageRoute=Ember.Route.extend({model:function(a){return a.image},setupController:function(a,b){a.set("model",b),a.set("imageUrl",b.url),a.set("imageExif",b.exif),1==b.publicImage?a.set("isPublic",!0):a.set("isPublic",!1),$.ajax({url:WS_HOST+"/public/upemgur/downloadImage/"+b.id,type:"GET"}).then(function(b){a.set("base64","data:image/jpg;base64,"+b)},function(){self.send("openErrorPopup","Error","Server not responding, Please try later...")})}})}(),function(){App.ClientImagesRoute=Ember.Route.extend({model:function(){return self=this,$.ajax({url:WS_HOST+"/upemgur/getImageUser",type:"GET",contentType:"application/json"}).then(function(a){return a},function(){window.location="http://localhost:8080/login"})},setupController:function(a,b){a.set("model",b.content)}})}(),function(){App.CreateUserRoute=Ember.Route.extend({model:function(){},setupController:function(){}})}(),function(){App.IndexRoute=Ember.Route.extend({beforeModel:function(){this.transitionTo(isAdmin?"adminImages":"clientImages")}})}(),function(){App.ModalView=Ember.View.extend({didInsertElement:function(){Ember.run.next(this,function(){this.$(".modal, .modal-backdrop").addClass("in")})},actions:{closeErrorView:function(){var a=this;this.$(".modal, .modal-backdrop").one("transitionend",function(){a.controller.send("closeErrorPopup")}),this.$(".modal, .modal-backdrop").removeClass("in")}}})}(),function(){App.Router.map(function(){this.resource("clientImages",{path:"images"},function(){this.resource("clientImage",{path:":image_id"},function(){})}),this.resource("adminImages",{path:"admin"},function(){this.resource("adminImage",{path:":image_id"},function(){}),this.resource("createUser",{path:"createUser"},function(){})})})}(),function(){WS_HOST="http://localhost:8080"}();