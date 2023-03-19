MyGame.screens['game-play'] = (function(game, input) {
    'use strict';

    let lastTimeStamp = performance.now();
    let cancelNextRequest = true;
    let myKeyboard = input.Keyboard();
    let startCountdown = true;
    let countDown = 0;
    let lastCountDown = Date.now();
    let score = 0;
    let ballSpeed = 1.25;
    let scoreWritten = false;
    let highScores = [];
    let pause = false;
    if (localStorage.getItem("highScores") !== null) {
        highScores = JSON.parse(localStorage.getItem("highScores"));
        console.log(highScores)
    } else {
        localStorage.setItem("highScores", JSON.stringify(highScores));
    }

    // Defines player paddle
    let myPlayer = MyGame.graphics.defineObject({
        imageSrc: 'assets/paddle.png',
        size: { width: MyGame.graphics.canvas.width / 8, height: 40 },
        center: { x: MyGame.graphics.canvas.width / 2, y: (MyGame.graphics.canvas.height - 80) },
        rotation: 0,
        moveRate: 0.5, // units per millisecond,
        type: "player",
        render: true,
        vector: {x: 1, y: 1}
    });

    let ball;

    let number = MyGame.graphics.defineObject({
        imageSrc: "assets/number1.png",
        size: { width: 50, height: 50 },
        center: { x: MyGame.graphics.canvas.width / 2, y: MyGame.graphics.canvas.height / 2 },
        rotation: 0,
        moveRate: 0, // units per millisecond
        type: "static",
        render: true
    })

    let myScoreText = MyGame.graphics.Text({
        text : 'Score: 0',
        font : '64px arial',
        fill : 'yellow',
        stroke : 'darkgreen',
        pos : {x : MyGame.graphics.canvas.width - 325, y : 25},
        rotation : 0
    });

    let pressFToLaunch = MyGame.graphics.Text({
        text : 'Press \'f\' to launch ball',
        font : '64px arial',
        fill : 'yellow',
        stroke : 'darkgreen',
        pos : {x : (MyGame.graphics.canvas.width / 2) - 300, y : (MyGame.graphics.canvas.height / 2) + 50},
        rotation : 0
    });

    let gameover = MyGame.graphics.Text({
        text : 'GAME OVER',
        font : '64px arial',
        fill : 'red',
        stroke : 'red',
        pos : {x : (MyGame.graphics.canvas.width / 2) - 200, y : MyGame.graphics.canvas.height / 2},
        rotation : 0
    });

    let heart1 = MyGame.graphics.defineObject({
        imageSrc: "assets/heart.png",
        size: { width: 50, height: 50 },
        center: { x: 50, y: 50 },
        rotation: 0,
        moveRate: 0, // units per millisecond
        type: "static",
        render: true
    });

    let heart2 = MyGame.graphics.defineObject({
        imageSrc: "assets/heart.png",
        size: { width: 50, height: 50 },
        center: { x: 105, y: 50 },
        rotation: 0,
        moveRate: 0, // units per millisecond
        type: "static",
        render: true
    });

    let heart3 = MyGame.graphics.defineObject({
        imageSrc: "assets/heart.png",
        size: { width: 50, height: 50 },
        center: { x: 160, y: 50 },
        rotation: 0,
        moveRate: 0, // units per millisecond
        type: "static",
        render: true
    })

    // Defines and creates all bricks for player to destroy
    let myBricks = [];

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
        if (!pause) {
            myPlayer.update(elapsedTime, myPlayer.texture);
            
            // Handles beginning game countdown
            if (startCountdown) {
                ball.texture.center.x = myPlayer.texture.center.x;
                countDown = lastCountDown - Date.now();

                if (countDown < -3000) {
                    startCountdown = false;
                    ball.texture.static = false;
                }

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
                }
            } else {
                number.texture.render = false;
                ball.update(elapsedTime, ball)

                if ((ball.texture.center.x <= (myPlayer.texture.center.x + myPlayer.texture.size.width / 2)) && (ball.texture.center.x >= (myPlayer.texture.center.x - myPlayer.texture.size.width / 2))) {
                    if ((ball.texture.center.y >= (myPlayer.texture.center.y - myPlayer.texture.size.height / 2))) {
                        ball.texture.vector.y = ball.texture.vector.y * -1;

                        let magnitude = Math.sqrt(Math.pow(ball.texture.vector.x, 2) + Math.pow(ball.texture.vector.y, 2));
                        ball.texture.vector.x = (ball.texture.vector.x + ((myPlayer.texture.vector.x * myPlayer.texture.moveRate))) / magnitude;
                    }
                }

                // collision detection for balls and bricks
                for (let i = 0; i < myBricks.length; i ++) {
                    for (let j = 0;  j < myBricks[i].length; j++) {
                        // left side
                        if (ball.texture.center.x > (myBricks[i][j].texture.center.x - (myBricks[i][j].texture.size.width / 2))) {
                            if (ball.texture.center.x < (myBricks[i][j].texture.center.x - ((myBricks[i][j].texture.size.width / 2) - 5))) {
                                if (ball.texture.center.y > (myBricks[i][j].texture.center.y - (myBricks[i][j].texture.size.height / 2))) {
                                    if (ball.texture.center.y < (myBricks[i][j].texture.center.y + (myBricks[i][j].texture.size.height / 2))) {
                                        ball.texture.vector.x = ball.texture.vector.x * -1;
                                        handleBrickCollision(myBricks[i][j]);
                                    }
                                }
                            }
                        }
                        // right side
                        if (ball.texture.center.x > (myBricks[i][j].texture.center.x + (myBricks[i][j].texture.size.width / 2))) {
                            if (ball.texture.center.x < (myBricks[i][j].texture.center.x + ((myBricks[i][j].texture.size.width / 2) + 5))) {
                                if (ball.texture.center.y > (myBricks[i][j].texture.center.y - (myBricks[i][j].texture.size.height / 2))) {
                                    if (ball.texture.center.y < (myBricks[i][j].texture.center.y + (myBricks[i][j].texture.size.height / 2))) {
                                        ball.texture.vector.x = ball.texture.vector.x * -1;
                                        handleBrickCollision(myBricks[i][j]);
                                    }
                                }
                            }
                        }
                        // bottom side
                        if (ball.texture.center.x > (myBricks[i][j].texture.center.x - (myBricks[i][j].texture.size.width / 2))) {
                            if (ball.texture.center.x < (myBricks[i][j].texture.center.x + (myBricks[i][j].texture.size.width / 2))) {
                                if (ball.texture.center.y < (myBricks[i][j].texture.center.y + (myBricks[i][j].texture.size.height / 2))) {
                                    if (ball.texture.center.y > (myBricks[i][j].texture.center.y + (myBricks[i][j].texture.size.height / 2) - 5)) {
                                        ball.texture.vector.y = ball.texture.vector.y * -1;
                                        handleBrickCollision(myBricks[i][j]);
                                    }
                                }
                            }
                        }
                        // top side
                        if (ball.texture.center.x > (myBricks[i][j].texture.center.x - (myBricks[i][j].texture.size.width / 2))) {
                            if (ball.texture.center.x < (myBricks[i][j].texture.center.x + (myBricks[i][j].texture.size.width / 2))) {
                                if (ball.texture.center.y > (myBricks[i][j].texture.center.y - (myBricks[i][j].texture.size.height / 2))) {
                                    if (ball.texture.center.y < (myBricks[i][j].texture.center.y - (myBricks[i][j].texture.size.height / 2) + 5)) {
                                        ball.texture.vector.y = ball.texture.vector.y * -1;
                                        handleBrickCollision(myBricks[i][j]);
                                    }
                                }
                            }
                        }
                    }
                }

                if (score !== -0 && !ball.texture.static) ball.texture.moveRate = ballSpeed + (score / 200);

                let topRowDestroyed = true;
                for (let i = 0; i < myBricks[0].length; i++) {
                    if (myBricks[0][i].texture.center.x !== 5000) {
                        topRowDestroyed = false;
                    }
                }
                if (topRowDestroyed) {
                    myPlayer.texture.size.width = MyGame.graphics.canvas.width / 16;
                } else {
                    myPlayer.texture.size.width = MyGame.graphics.canvas.width / 8;
                }
        }   
    } else {
        let pauseMenu = {
            center: {x: 50, y: 50},
            rotation: 0,
            outlineColor: "blue",
            fillColor: "blue",
            width: 200,
            height: 200
        };

        MyGame.graphics.drawRectangle(pauseMenu)
    }

    function handleBrickCollision(brick) {
        switch(brick.texture.imageSrc) {
            case("assets/yellowBrick.png"):
                score += 1;
                break;
            case("assets/orangeBrick.png"):
                score += 2;
                break;
            case("assets/blueBrick.png"):
                score += 3;
                break;
            case("assets/greenBrick.png"):
                score += 5;
                break;
        }

        myScoreText = MyGame.graphics.Text({
            text : 'Score: ' + score,
            font : '64px arial',
            fill : 'yellow',
            stroke : 'darkgreen',
            pos : {x : MyGame.graphics.canvas.width - 325, y : 25},
            rotation : 0
        });
        
        brick.texture.center.x = 5000;   
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

        myScoreText.draw();

        // Draws bricks onto screen
        for (let i = 0; i < myBricks.length; i++) {
            for (let j = 0; j < myBricks[i].length; j++) {
                MyGame.graphics.drawTexture(myBricks[i][j].texture.image, myBricks[i][j].texture.center, myBricks[i][j].texture.rotation, myBricks[i][j].texture.size)
            }
        }

        if (ball.texture.lives >= 0) {
            MyGame.graphics.drawTexture(heart1.texture.image, heart1.texture.center, heart1.texture.rotation, heart1.texture.size);
        }

        if (ball.texture.lives >= 1) {
            MyGame.graphics.drawTexture(heart2.texture.image, heart2.texture.center, heart2.texture.rotation, heart2.texture.size);
        }

        if (ball.texture.lives >= 2) {
            MyGame.graphics.drawTexture(heart3.texture.image, heart3.texture.center, heart3.texture.rotation, heart3.texture.size);
        }

        if (ball.texture.lives < 0) {
            gameover.draw();
            localStorage.setItem("highScores", JSON.stringify(highScores))
            ball.texture.center.y = MyGame.graphics.canvas.height * 2;
            myScoreText = MyGame.graphics.Text({
                text : '' + score,
                font : '64px arial',
                fill : 'yellow',
                stroke : 'darkgreen',
                pos : {x : (MyGame.graphics.canvas.width / 2) - 25, y : (MyGame.graphics.canvas.height / 2) + 100},
                rotation : 0
            });

            if (!scoreWritten) {
                highScores.push(score);
                scoreWritten = true;
            }
        }

        if (ball.texture.static) {
            pressFToLaunch.draw()
        }

        if (pause) {
            let pauseMenu = {
                center: {x: MyGame.graphics.canvas.width / 2, y: MyGame.graphics.canvas.height / 2},
                rotation: 0,
                outlineColor: "black",
                fillColor: "black",
                width: MyGame.graphics.canvas.width / 2,
                height: MyGame.graphics.canvas.height / 4
            };

            let resume = MyGame.graphics.Text({
                text : 'Resume (esc)',
                font : '64px arial',
                fill : 'white',
                stroke : 'green',
                pos : {x : (MyGame.graphics.canvas.width / 2 - 175), y : (MyGame.graphics.canvas.height / 2 - 100)},
                rotation : 0
            });
            let quit = MyGame.graphics.Text({
                text : 'Quit (Q)',
                font : '64px arial',
                fill : 'white',
                stroke : 'green',
                pos : {x : (MyGame.graphics.canvas.width / 2 - 125), y : (MyGame.graphics.canvas.height / 2 + 50)},
                rotation : 0
            });

            MyGame.graphics.drawRectangle(pauseMenu)
            resume.draw();
            quit.draw();
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
            // cancelNextRequest = true;
            pause = !pause;
            //
            // Then, return to the main menu
            // game.showScreen('main-menu');
        });

        myKeyboard.register('f', function() {
            ball.texture.static = false;
            ball.texture.moveRate = ballSpeed;
        });
        myKeyboard.register('q', function() {
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
        lastCountDown = Date.now();

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

        ball = MyGame.graphics.defineObject({
            imageSrc: 'assets/ball.png',
            size: { width: 20, height: 20},
            center: { x: MyGame.graphics.canvas.width / 2, y: MyGame.graphics.canvas.height - (MyGame.graphics.canvas.height / 8)},
            rotation: 0,
            moveRate: ballSpeed,
            type: "moveable",
            render: true,
            vector: {startDirection: 3, x: 1, y: -1},
            lives: 2,
            static: true
        });
        myScoreText = MyGame.graphics.Text({
            text : 'Score: 0',
            font : '64px arial',
            fill : 'yellow',
            stroke : 'darkgreen',
            pos : {x : MyGame.graphics.canvas.width - 325, y : 25},
            rotation : 0
        });

        ball.texture.static = true;
        startCountdown = true;
        scoreWritten = false;
        score = 0;

        requestAnimationFrame(gameLoop);
    }

    return {
        initialize : initialize,
        run : run,
        player: myPlayer
    };

}(MyGame.game, MyGame.input));
