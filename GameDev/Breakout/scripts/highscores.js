MyGame.screens['high-scores'] = (function(game) {
    'use strict';
    let highScore1, highScore2, highScore3, highScore4;
    
    function initialize() {
        document.getElementById('id-high-scores-back').addEventListener(
            'click',
            function() { game.showScreen('main-menu'); });
    }
    
    function run() {
        highScore1 = document.getElementById("high-scores-1");
        highScore2 = document.getElementById("high-scores-2");
        highScore3 = document.getElementById("high-scores-3");
        highScore4 = document.getElementById("high-scores-4");

        let highScores = JSON.parse(localStorage.getItem("highScores"));
        if (highScores[highScores.length - 1] !== undefined) {
            highScore1.innerHTML = "" + highScores[highScores.length - 1];
        }
        if (highScores[highScores.length - 2] !== undefined) {
            highScore2.innerHTML = "" + highScores[highScores.length - 2];
        }
        if (highScores[highScores.length - 3] !== undefined) {
            highScore3.innerHTML = "" + highScores[highScores.length - 3];
        }
        if (highScores[highScores.length - 4] !== undefined) {
            highScore4.innerHTML = "" + highScores[highScores.length - 4];
        }
    }
    
    return {
        initialize : initialize,
        run : run
    };
}(MyGame.game));
