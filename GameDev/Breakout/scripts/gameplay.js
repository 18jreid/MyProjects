MyGame.screens['game-play'] = (function(game, input) {
    'use strict';

    let lastTimeStamp = performance.now();
    let cancelNextRequest = true;
    let myKeyboard = input.Keyboard();
    let startCountdown = true;
    let countDown = 0;
    let lastCountDown = Date.now();

    // Defines player paddle
    let myPlayer = MyGame.graphics.defineObject({
        imageSrc: 'assets/paddle.png',
        size: { width: MyGame.graphics.canvas.width / 8, height: 40 },
        center: { x: MyGame.graphics.canvas.width / 2, y: (MyGame.graphics.canvas.height - 80) },
        rotation: 0,
        moveRate: 0.5, // units per millisecond,
        type: "player",
        render: true
    });

    let ball = MyGame.graphics.defineObject({
        imageSrc: 'assets/ball.png',
        size: { width: 20, height: 20},
        center: { x: MyGame.graphics.canvas.width / 2, y: MyGame.graphics.canvas.height - (MyGame.graphics.canvas.height / 8)},
        rotation: 0,
        moveRate: 5,
        type: "moveable",
        render: true,
        vector: {startDirection: giveRandomUpwardsDirection(), x: 1, y: 1}
    });

    let number = MyGame.graphics.defineObject({
        imageSrc: "assets/number1.png",
        size: { width: 50, height: 50 },
        center: { x: MyGame.graphics.canvas.width / 2, y: MyGame.graphics.canvas.height / 2 },
        rotation: 0,
        moveRate: 0, // units per millisecond
        type: "static",
        render: true
    })

    // Defines and creates all bricks for player to destroy
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
                type: "static",
                render: true
            });

            row.push(brick);
        }
        myBricks[i] = row;
    }

    // Used to find correct color of brick for row
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

    function giveRandomUpwardsDirection() {
        return Math.random() * Math.PI;
    }

    // Processes user input from the keyboard
    function processInput(elapsedTime) {
        myKeyboard.update(elapsedTime);
    }

    // update function used for objects
    function update() {
        let elapsedTime = lastTimeStamp - performance.now();
        myPlayer.update(elapsedTime, myPlayer.texture);
        
        if (startCountdown) {
            ball.texture.center.x = myPlayer.texture.center.x;
            countDown = lastCountDown - Date.now();

            if (countDown < -3000) startCountdown = false;

            if (Math.abs(countDown) > 0 && Math.abs(countDown) <= 1000) {
                number = MyGame.graphics.defineObject({
                    imageSrc: "assets/number1.png",
                    size: { width: 50, height: 50 },
                    center: { x: MyGame.graphics.canvas.width / 2, y: MyGame.graphics.canvas.height / 2 },
                    rotation: 0,
                    moveRate: 0, // units per millisecond
                    type: "static",
                    render: true
                });
            } else if (Math.abs(countDown) > 1000 && Math.abs(countDown) <= 2000) {
                number = MyGame.graphics.defineObject({
                    imageSrc: "assets/number2.png",
                    size: { width: 50, height: 50 },
                    center: { x: MyGame.graphics.canvas.width / 2, y: MyGame.graphics.canvas.height / 2 },
                    rotation: 0,
                    moveRate: 0, // units per millisecond
                    type: "static",
                    render: true
                });
            } else if (Math.abs(countDown) > 2000 && Math.abs(countDown) <= 3000) {
                number = MyGame.graphics.defineObject({
                    imageSrc: "assets/number3.png",
                    size: { width: 50, height: 50 },
                    center: { x: MyGame.graphics.canvas.width / 2, y: MyGame.graphics.canvas.height / 2 },
                    rotation: 0,
                    moveRate: 0, // units per millisecond
                    type: "static",
                    render: true
                });

            giveRandomUpwardsDirection();
            }
        } else {
            number.texture.render = false;

            ball.texture.center.x += (elapsedTime * ball.texture.moveRate) * ball.texture.vector.x;
            ball.texture.center.y += (elapsedTime * ball.texture.moveRate) * ball.texture.vector.y;

                if (ball.texture.center.x <= ball.texture.size.width / 2) {
                    ball.texture.center.x += (ball.texture.size.width / 6);
                    ball.texture.vector.x *= -1;
                }
                if (ball.texture.center.x >= MyGame.graphics.canvas.width - (ball.texture.size.width / 2)) {
                    ball.texture.center.x -= (ball.texture.size.width / 6);
                    ball.texture.vector.x = 1;
                }

                if (ball.texture.center.y <= ball.texture.size.height / 2) {
                    ball.texture.center.y += (ball.texture.size.width / 6);
                    ball.texture.vector.y *= -1;
                }
                if (ball.texture.center.y >= MyGame.graphics.canvas.height - (ball.texture.size.height / 2)) {
                    ball.texture.center.y -= (ball.texture.size.height / 6);
                    ball.texture.vector.y *= -1;
                }
        }
    }

    // Renders object to the screen
    function render() {
        // Clear canvas
        MyGame.graphics.clear();

        // Render Player to screen
        MyGame.graphics.drawTexture(myPlayer.texture.image, myPlayer.texture.center, myPlayer.texture.rotation, myPlayer.texture.size);

        // Draw ball to screen
        MyGame.graphics.drawTexture(ball.texture.image, ball.texture.center, ball.texture.rotation, ball.texture.size);

        if (number.texture.render === true) {
            MyGame.graphics.drawTexture(number.texture.image, number.texture.center, number.texture.rotation, number.texture.size);
        }

        // Draws bricks onto screen
        for (let i = 0; i < myBricks.length; i++) {
            for (let j = 0; j < myBricks[i].length; j++) {
                MyGame.graphics.drawTexture(myBricks[i][j].texture.image, myBricks[i][j].texture.center, myBricks[i][j].texture.rotation, myBricks[i][j].texture.size)
            }
        }
    }

    // Loops are game continuously until interrupted
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

    // Initialized current states desired starting features
    function initialize() {
        myKeyboard.register('a', myPlayer.moveLeft);
        myKeyboard.register('d', myPlayer.moveRight);

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
