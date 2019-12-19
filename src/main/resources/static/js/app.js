var jsonloader = new THREE.JSONLoader,
    textureloader = new THREE.TextureLoader,
    enter_scene_event = new CustomEvent("p-enter-scene", {});

function filter_platform_content() {
    var a = is_mobile_browser() ? document.querySelectorAll(".platform_mobile") : document.querySelectorAll(".platform_desktop");
    for (var b = 0; b < a.length; b++) a[b].style.display = ""
}
function is_mobile_browser() {
    return -1 < navigator.userAgent.toLowerCase().indexOf("android") || -1 < navigator.userAgent.toLowerCase().indexOf("iphone")
}
THREE.DefaultLoadingManager.onProgress = function (a, b, d) {
    var f = document.getElementById("loading_progress");
    var c = document.getElementById("loading_percentage");
    console.log( 'Loading file: ' + a + '.\nLoaded ' + b + ' of ' + d + ' files.' );
    a && c && (b = Math.round(b / 24 * 100), f.style.width = b+"%", c.innerHTML = "加载数据包（"+b*.25+"MB)");
    if(b=="100"){loading_complete();}
};
function loading_complete() {
    var a = document.getElementById("loading_progress"),
        b = document.getElementById("loading_percentage"),
        d = document.getElementById("loading_overlay"),
        c = document.getElementById("loading_enter_box");
    a && b && d && c && (a.className = "loading_in_progress loading_complete", b.innerHTML = "开始", c.onclick = enter_scene)
};

function enter_scene() {
    document.dispatchEvent(enter_scene_event);
    var a = document.getElementById("loading_overlay");
    if (a) {
        a.style.opacity = "0";
        var b = document.getElementById("scene_ui");
        b && (b.style.opacity = "1");
        toggle_help();
        setTimeout(function () {
            a.style.display = "none";
            var b = document.getElementById("loading_percentage");
            b && (b.innerHTML = "返回")
        }, 1E3)
    }
}

function enter_vr() {
    var a = document.querySelector("a-scene");
    a && (a.hasLoaded ? a.enterVR() : a.addEventListener("loaded", a.enterVR))
}

function toggle_help() {
    var a = document.getElementById("loading_overlay"),
        b = document.getElementById("scene_ui");
    a && b && "none" == a.style.display && (a.style.display = "initial", setTimeout(function () {
        a.style.opacity = "1"
    }, 1), b.style.opacity = "0")
}

function toggle_info() {
    var a = document.getElementById("infomation_overlay"),
        b = document.getElementById("scene_ui");
    a && b && "none" == a.style.display && (a.style.display = "initial", setTimeout(function () {
        a.style.opacity = "1"
    }, 1), b.style.opacity = "0")
}

function close_info() {
    var a = document.getElementById("infomation_overlay");
    if (a) {
        a.style.opacity = "0";
        var b = document.getElementById("scene_ui");
        b && (b.style.opacity = "1");
        setTimeout(function () {
            a.style.display = "none";
        }, 1E3)
    }
}
function toggle_help() {
    var a = document.getElementById("help_layer"),
        b = document.getElementById("scene_ui");
        a.style.display = "block";
}

function close_help() {
    var a = document.getElementById("help_layer");
     a.style.display = "none";
    
}

document.addEventListener("p-enter-scene", function (a) {
    "true" == scene_ui_mute.getAttribute("data-state") && document.getElementById("scene_ui_mute").click()
}, !1);

function toggle_mute() {
    var a = document.getElementById("scene_ui_mute");
    if (a) {
        a = a.getAttribute("data-state");
        for (var c = document.getElementsByClassName("sound"), b = 0; b < c.length; b++) "true" == a ? c[b].components.sound.pauseSound() : c[b].components.sound.playSound()
    }
}




function scene_ui_toggle_button_state(a, b, d, c, e) {
    if ("true" == a.getAttribute("data-state")) {
        if (a.setAttribute("data-state", "false"), a.className = a.className.replace(c, e), a = document.getElementById(a.id + "_tooltip")) a.innerHTML = d
    } else if (a.setAttribute("data-state", "true"), a.className = a.className.replace(e, c), a = document.getElementById(a.id + "_tooltip")) a.innerHTML = b
}

AFRAME.registerComponent('fenced', {
    schema: {
      xMin: {type: 'number', default: -10},
      xMax: {type: 'number', default:  10},
      zMin: {type: 'number', default: -10},
      zMax: {type: 'number', default:  10}
    },
    tick: function() {
        var data = this.data;
        var position = this.el.getAttribute('position');
        position.x = Math.min( data.xMax, position.x);
        position.x = Math.max( data.xMin, position.x);
        position.z = Math.min( data.zMax, position.z);
        position.z = Math.max( data.zMin, position.z);
        this.el.setAttribute('position', position);
    }
  });
  
   AFRAME.registerComponent('text-on-mouseenter', {
        //dependencies: ['raycaster'],
        schema: {
        	text: {default: ''}
        },
        init: function () {
            var data = this.data;
            var el = this.el;
            var text_box = document.getElementById("bottom_text_info");
            el.addEventListener('mouseenter', function () {
            	text_box.innerHTML=data.text;
            	el.setAttribute('material', {opacity: 0.2});
            });
            el.addEventListener('mouseleave', function () {
            	text_box.innerHTML='';
            	el.setAttribute('material', {opacity: 0});
            });

        }
    });
    
       AFRAME.registerComponent('text1-on-mouseenter', {
        //dependencies: ['raycaster'],
        schema: {
        	text: {default: ''}
        },
        init: function () {
            var data = this.data;
            var el = this.el;
            var text_box = document.getElementById("bottom_text_info");
            el.addEventListener('mouseenter', function () {
            	text_box.innerHTML=data.text;
            });
            el.addEventListener('mouseleave', function () {
            	text_box.innerHTML='';

            });

        }
    });
    
    /*
     AFRAME.registerComponent('move-here', {
        schema: {
            text: {default: '点击移动'}
        },
        init: function () {
            var data = this.data;
            var el = this.el;
            var text_box = document.getElementById("bottom_text_info");
            el.addEventListener('mouseenter', function () {
                text_box.innerHTML=data.text;
                el.setAttribute('material', {opacity: 0.2});
            });
            el.addEventListener('mouseleave', function () {
                text_box.innerHTML='';
                el.setAttribute('material', {opacity: 0});
            });

        }
    });
    */
         AFRAME.registerComponent('pic-on-mouseenter', {
        //dependencies: ['raycaster'],
        schema: {
        	text: {default: ''},
        	pic: {default: ''}
        },
        init: function () {
            var data = this.data;
            var el = this.el;
            var pic_box = document.getElementById("left_pic_info");
            //var pic_world = document.getElementById("right_pic_info");
            // console.log(data);
            // console.log(el);
            // console.log(pic_world);
            el.addEventListener('mouseenter', function () {
            	pic_box.style.left=0;
            	pic_box.innerHTML="<h3>"+data.text+"</h3><img src="+ data.pic +">";

                // pic_world.style.right=0;
                // pic_world.innerHTML="<h3>"+data.text+"</h3>";

            });
            el.addEventListener('mouseleave', function () {
            	//pic_box.innerHTML='';
            	pic_box.style.left="-36vw"

                // pic_world.style.right="-36vw"
            });

        }
    });