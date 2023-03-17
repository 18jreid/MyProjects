MyGame.screens['game-play'] = (function(game, input) {
    'use strict';

    let lastTimeStamp = performance.now();
    let cancelNextRequest = true;
    let myKeyboard = input.Keyboard();
    let myPlayer = MyGame.graphics.defineObject({
        imageSrc: 'assets/paddle.png',
        size: { width: MyGame.graphics.canvas.width / 8, height: 40 },
        center: { x: MyGame.graphics.canvas.width / 2, y: (MyGame.graphics.canvas.height - 80) },
        rotation: 0,
        moveRate: 1, // units per millisecond,
        type: "player"
    });

    let myBricks = [];
    for (let i = 0; i < 8; i++) {
        let row = [];
        for (let j = 0; j < 14; j++) {
            let brick = MyGame.graphics.defineObject({
                imageSrc: findRowColor(i),
                size: { width: 50, height: 25 },
                center: { x: 150 + (j * 55), y: 150 + (i * 30) },
                rotation: 0,
                moveRate: 0, // units per millisecond
                type: "static"
            });

            row.push(brick);
        }
        myBricks[i] = row;
    }

    function findRowColor(index) {
        let src = "assets/greenBrick.png";
        if (index === 0 || index === 1) {
            return "assets/greenBrick.png";
        }
        else if (index === 2 || index === 3) {
            return "assets/blueBrick.png";
        }
        else if (index === 4 || index === 5) {
            return "assets/orangeBrick.png";
        }
        else if (index === 6 || index === 7) {
            return "assets/yellowBrick.png";
        }
    }

    function processInput(elapsedTime) {
        myKeyboard.update(elapsedTime);
    }

    function update() {
    }

    function render() {
        MyGame.graphics.clear();
        MyGame.graphics.drawTexture(myPlayer.texture.image, myPlayer.texture.center, myPlayer.texture.rotation, myPlayer.texture.size);
        for (let i = 0; i < myBricks.length; i++) {
            for (let j = 0; j < myBricks[i].length; j++) {
                MyGame.graphics.drawTexture(myBricks[i][j].texture.image, myBricks[i][j].texture.center, myBricks[i][j].texture.rotation, myBricks[i][j].texture.size)
            }
        }
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
        myKeyboard.register('a', myPlayer.moveLeft);
        myKeyboard.register('d', myPlayer.moveRight);
        myKeyboard.register('w', myPlayer.moveUp);
        myKeyboard.register('s', myPlayer.moveDown);

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
