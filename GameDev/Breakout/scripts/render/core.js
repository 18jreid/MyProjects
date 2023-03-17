MyGame.graphics = (function() {
    'use strict';

    let canvas = document.getElementById('id-canvas');
    let context = canvas.getContext('2d');

    function clear() {
        context.clearRect(0, 0, canvas.width, canvas.height);
    }

    function drawRectangle(spec) {
        context.save();

        context.translate(spec.center.x, spec.center.y);
        context.rotate(spec.rotation);
        context.translate(-spec.center.x, -spec.center.y);

        context.strokeStyle = spec.outlineColor;
        context.fillStyle = spec.fillColor;

        context.fillRect(spec.center.x - spec.width / 2, spec.center.y - spec.height / 2, spec.width, spec.height);

        context.strokeRect(spec.center.x - spec.width / 2, - spec.center.y - spec.height / 2, spec.width, spec.height);

        context.restore();
    }

    // --------------------------------------------------------------
    //
    // Draws a texture to the canvas with the following specification:
    //    image: Image
    //    center: {x: , y: }
    //    size: { width: , height: }
    //
    // --------------------------------------------------------------
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

    function defineObject(spec) {
        let that = {};
    
        spec.image = new Image();
        spec.image.ready = false;
        spec.image.onload = function() {
            this.ready = true;
        };
        spec.image.src = "file:///C:/Users/jsrei/Desktop/MyProjects/GameDev/Breakout/" + spec.imageSrc;
    
        function moveLeft(elapsedTime) {
            spec.center.x -= spec.moveRate * elapsedTime;
        }
        function moveRight(elapsedTime) {
            spec.center.x += spec.moveRate * elapsedTime;
        }
        function moveUp(elapsedTime) {
            spec.center.y -= spec.moveRate * elapsedTime;
        }
        function moveDown(elapsedTime) {
            spec.center.y += spec.moveRate * elapsedTime;
        }
    
        that.moveUp = moveUp;
        that.moveDown = moveDown;
        that.moveLeft = moveLeft;
        that.moveRight = moveRight;
    
        that.texture = spec;
    
        return that;
    }

    let api = {
        get canvas() { return canvas; },
        clear: clear,
        drawTexture: drawTexture,
        drawText: drawText,
        drawRectangle: drawRectangle,
        defineObject: defineObject,
        width : canvas.width,
        height : canvas.height
    };

    return api;
}());
