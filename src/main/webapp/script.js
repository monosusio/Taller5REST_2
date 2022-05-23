const $btnIniciarSesion= document.querySelector('.Singboton'),
    $btnRegistrase = document.querySelector('.Regboton'),
    $Registrarse = document.querySelector('.Registrarse'),
    $IniciarSesion = document.querySelector('.IniciarSesion');


var formulario = document.getElementById("login");
formulario.addEventListener("submit",function(e){
    e.preventDefault();

    var data = {
        "username": document.getElementById("username").value,
        "password": document.getElementById("password").value,
    };

    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append("Access-Control-Allow-Origin", "*");
    var newdata= fetch("./api/users/found",{method: "POST",
        body: JSON.stringify(data),
        headers: myHeaders
    })
        .then(res =>
            res.json()
        )
        .then(dato => {
            localStorage.setItem("username",dato["username"]);
            localStorage.setItem("role",dato["role"]);
            localStorage.setItem("role",dato["fcoins"]);
            console.log("cambipo nuevo en javascript");
            /*if(dato["role"] == "Artist"){
                //window.location.href = "http://localhost:8080/Taller4-1.0-SNAPSHOT/Artista.html";
                console.log("si funciona para artista");
            }else if(dato["role"] == "Custumer"){
                //window.location.href = "http://localhost:8080/Taller4-1.0-SNAPSHOT/comprador.html";
            }*/

       })
    //(Response.status == 200){  window.location.href="./index.html"; }
    console.log(["username"]);
})


document.addEventListener('click', e=>{
    if (e.target === $btnIniciarSesion || e.target === $btnRegistrase){
        $IniciarSesion.classList.toggle('active');
        $Registrarse.classList.toggle('active')
    }
})

