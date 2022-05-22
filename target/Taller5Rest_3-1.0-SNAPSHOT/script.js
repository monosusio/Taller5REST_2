const $btnIniciarSesion= document.querySelector('.Singboton'),
    $btnRegistrase = document.querySelector('.Regboton'),
    $Registrarse = document.querySelector('.Registrarse'),
    $IniciarSesion = document.querySelector('.IniciarSesion');

var formulario=document.getElementById("formulario1");

formulario.addEventListener("submit",function (e){

    e.preventDefault();

    console.log("se estan pulsando los botones");

})

var data = {
    "username": document.getElementById("nombre").value,
    "password": document.getElementById("contraseña").value,
    "rol": document.getElementById("rol").value,

};

//const myHeaders = new Headers();
//fetch("")

document.addEventListener('click', e=>{
    if (e.target === $btnIniciarSesion || e.target === $btnRegistrase){
        $IniciarSesion.classList.toggle('active');
        $Registrarse.classList.toggle('active')
    }

})

document.addEventListener('click', e=>{
    if (e.target === $btnIniciarSesion || e.target === $btnRegistrase){
        $IniciarSesion.classList.toggle('active');
        $Registrarse.classList.toggle('active')
    }
})

