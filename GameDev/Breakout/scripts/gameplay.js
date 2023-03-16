MyGame.screens['game-play'] = (function(game, input) {
    'use strict';

    let lastTimeStamp = performance.now();
    let cancelNextRequest = true;

    let myKeyboard = input.Keyboard();

    function processInput(elapsedTime) {
        myKeyboard.update(elapsedTime);
    }

    function defineObject(spec) {
        let that = {};
    
        spec.image = new Image();
        spec.image.ready = false;
        spec.image.onload = function() {
            this.ready = true;
        };
        spec.image.src = spec.imageSrc;
    
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

    function update() {
    }

    function render() {
        
        MyGame.graphics.drawTexture(myObject.texture);
    }

    function gameLoop(time) {
        let elapsedTime = time - lastTimeStamp;
        lastTimeStamp = time;

        processInput(elapsedTime);
        update();
        render();

        if (!cancelNextRequest) {
            requestAnimationFrame(gameLoop);
        }
    }

    function initialize() {
        myKeyboard.register('Escape', function() {
            //
            // Stop the game loop by canceling the request for the next animation frame
            cancelNextRequest = true;
            //
            // Then, return to the main menu
            game.showScreen('main-menu');
        });
    }

    function run() {
        lastTimeStamp = performance.now();
        cancelNextRequest = false;
        requestAnimationFrame(gameLoop);
    }

    return {
        initialize : initialize,
        run : run
    };

}(MyGame.game, MyGame.input));
