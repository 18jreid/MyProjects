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
            function update(elapsedTime, ball) {
                ball.texture.center.x += (ball.texture.moveRate) * ball.texture.vector.x;
                ball.texture.center.y += (ball.texture.moveRate) * ball.texture.vector.y;

                if (ball.texture.center.x > (canvas.width -(ball.texture.size.width / 2))) {
                    ball.texture.vector.x = ball.texture.vector.x * -1;
                }
                if (ball.texture.center.x < (ball.texture.size.width / 2)) {
                    ball.texture.vector.x = ball.texture.vector.x * -1;
                }
                if (ball.texture.center.y > (canvas.height - (ball.texture.size.height / 2))) {
                    if (ball.texture.lives >= 0) {
                        ball.texture.lives -= 1;
                        ball.texture.moveRate = 0;
                        ball.texture.static = true;
                        ball.texture.center.y = canvas.height - 115;
                    }
                }
                if (ball.texture.center.y < ball.texture.size.height / 2) {
                    ball.texture.vector.y = ball.texture.vector.y * -1;
                }
                
            }
        
            that.update = update;
        }
    
        that.texture = spec;
    
        return that;
    }

    //------------------------------------------------------------------
    //
    // This is used to create a text function that can be used by client
    // code for rendering.
    //
    //------------------------------------------------------------------
    function Text(spec) {
        let that = {};
        
        that.updateRotation = function(angle) {
            spec.rotation += angle;
        };

        //------------------------------------------------------------------
        //
        // This returns the height of the specified font, in pixels.
        //
        //------------------------------------------------------------------
        function measureTextHeight(spec) {
            context.save();

            context.font = spec.font;
            let height = context.measureText('m').width;

            context.restore();

            return height;
        }

        //------------------------------------------------------------------
        //
        // This returns the width of the specified font, in pixels.
        //
        //------------------------------------------------------------------
        function measureTextWidth(spec) {
            context.save();

            context.font = spec.font;
            let width = context.measureText(spec.text).width;

            context.restore();
            
            return width;
        }

        that.draw = function() {
            context.save();

            context.font = spec.font;
            context.fillStyle = spec.fill;
            context.strokeStyle = spec.stroke;
            context.textBaseline = 'top';

            context.translate(spec.pos.x + that.width / 2, spec.pos.y + that.height / 2);
            context.rotate(spec.rotation);
            context.translate(-(spec.pos.x + that.width / 2), -(spec.pos.y + that.height / 2));

            context.fillText(spec.text, spec.pos.x, spec.pos.y);
            context.strokeText(spec.text, spec.pos.x, spec.pos.y);

            context.restore();
        };

        //
        // Compute and expose some public properties for this text.
        that.height = measureTextHeight(spec);
        that.width = measureTextWidth(spec);
        that.pos = spec.pos;

        return that;
    }

    let api = {
        get canvas() { return canvas; },
        clear: clear,
        drawTexture: drawTexture,
        drawText: drawText,
        defineObject: defineObject,
        Text : Text
    };

    return api;
}());
