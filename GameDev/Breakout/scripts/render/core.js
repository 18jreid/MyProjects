MyGame.graphics = (function() {
    'use strict';

    let canvas = document.getElementById('id-canvas');
    let context = canvas.getContext('2d');

    // Clears canvas of all drawings
    function clear() {
        context.clearRect(0, 0, canvas.width, canvas.height);
    }

    // Draws desired texture to canvas
    function drawTexture(image, center, rotation, size) {
        context.save();

        context.translate(center.x, center.y);
        context.rotate(rotation);
        context.translate(-center.x, -center.y);

        context.drawImage(
            image,
            center.x - size.width / 2,
            center.y - size.height / 2,
            size.width, size.height);

        context.restore();
    }

    // Draws desired text to canvas
    function drawText(spec) {
        context.save();

        context.font = spec.font;
        context.fillStyle = spec.fillStyle;
        context.strokeStyle = spec.strokeStyle;
        context.textBaseline = 'top';

        context.translate(spec.position.x, spec.position.y);
        context.rotate(spec.rotation);
        context.translate(-spec.position.x, -spec.position.y);


        context.fillText(spec.text, spec.position.x, spec.position.y);
        context.strokeText(spec.text, spec.position.x, spec.position.y);

        context.restore();
    }

    // Defines textured object to be drawn to the screen
    function defineObject(spec) {
        let that = {};
    
        spec.image = new Image();
        spec.image.ready = false;
        spec.image.onload = function() {
            this.ready = true;
        };
        let pathName = window.location.pathname.replace("index.html", "");
        spec.image.src = pathName + spec.imageSrc;
    
        if (spec.type === "player") {
            function moveLeft(elapsedTime) {
                spec.center.x -= spec.moveRate * elapsedTime;
            }
            function moveRight(elapsedTime) {
                spec.center.x += spec.moveRate * elapsedTime;
            }
            function update(elapsedTime, object) {
                if (object.center.x <= object.size.width / 2) {
                    object.center.x += (object.size.width / 6);
                }
                if (object.center.x >= canvas.width - (object.size.width / 2)) {
                    object.center.x -= (object.size.width / 6);
                }
            }
        
            that.moveLeft = moveLeft;
            that.moveRight = moveRight;
            that.update = update;
        }

        if (spec.type === "moveable") {
            function update(elapsedTime, object) {
        
            }
        
            that.update = update;
        }
    
        that.texture = spec;
    
        return that;
    }

    let api = {
        get canvas() { return canvas; },
        clear: clear,
        drawTexture: drawTexture,
        drawText: drawText,
        defineObject: defineObject
    };

    return api;
}());
