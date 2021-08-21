<template>
  <div class="home">
    <div class="hero">
      <nav>
        <p>Click to see beer</p>
        <button class="togg" type="button" @click="toggleBtn()" id="btn">
          <span></span>
        </button>
      </nav>

      <div class="lamp-container">
        <img src="https://i.postimg.cc/yYz5Nnqw/lamp.png" alt="" class="lamp" />
        <img
          src="https://i.postimg.cc/SxgtDhXh/light.png"
          alt=""
          class="light"
          id="light"
        />
        <img src="../assets/beer.png" alt="" class="beer" id="beer" />
      </div>

      <div class="text-container">
        <h1>Welcome to the website with a variety of beers. Enjoy!</h1>
        <p>This is just one of many of our projects.</p>
        <a href="/beers">Check All Beers</a>
      </div>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src

// import axios from "axios";
export default {
  name: "Home",
  data() {
    return {
      data: "",
     
    };
  },
  methods: {
    toggleBtn() {
      var btn = document.getElementById("btn");
      var light = document.getElementById("light");
      var beer = document.getElementById("beer");
      btn.classList.toggle("active");
      light.classList.toggle("on");
      beer.classList.toggle("on");
    },
  },
  mounted() {},
};
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  font-family: sans-serif;
  box-sizing: border-box;
}

.hero {
  background: #1d2026;
  min-height: 100vh;
  width: 100%;
  color: #fff;
  position: relative;
}
.togg{
  margin-left: 10px;
}
nav {
  display: flex;
  align-items: center;
  padding: 20px 8%;
}

nav .menu-img {
  width: 25px;
  margin-right: 20px;
  cursor: pointer;
}

nav .logo {
  width: 160px;
  cursor: pointer;
}
nav ul {
  flex: 1;
  text-align: right;
}
nav ul li {
  display: inline-block;
  list-style: none;
}
nav ul li a {
  text-decoration: none;
  color: #fff;
}
button {
  background: #efefef;
  height: 30px;
  width: 60px;
  border-radius: 20px;
  border: 0;
  outline: 0;
  cursor: pointer;
  transition: background 0.5s;
}
button span {
  display: block;
  background: #999;
  height: 26px;
  width: 26px;
  border-radius: 50%;
  margin-left: 2px;
  transition: background 0.5s, margin-left 0.5s;
}
.lamp-container {
  position: absolute;
  top: -20px;
  left: 22%;
  width: 200px;
}
.lamp {
  width: 100%;
}
.light {
  position: absolute;
  top: 97%;
  left: 50%;
  transform: translateX(-50%);
  width: 700px;
  margin-left: -10px;
  opacity: 0;
  transition: opacity 0.5s;
}
.text-container {
  max-width: 700px;
  margin-top: 7%;
  margin-left: 50%;
}
.text-container h1 {
  font-size: 70px;
  font-weight: 400;
  text-align: center;
}
.text-container p{
  font-size: 30px;
}
.text-container a {
  text-decoration: none;
  background: #00986f;
  padding: 14px 40px;
  display: inline-block;
  color: #fff;
  font-size: 18px;
  margin-top: 30px;
  border-radius: 30px;
}
.control {
  display: flex;
  align-items: center;
  justify-content: flex-end;
  margin-top: 150px;
}
.control .line {
  width: 250px;
  height: 4px;
  background: #fff;
  margin: 0 20px;
  border-radius: 2px;
}
.control .line span {
  width: 50%;
  height: 8px;
  margin-top: -2px;
  border-radius: 4px;
  background: #00986f;
  display: block;
}
.active {
  background: green;
}
.active span {
  background: #fff;
  margin-left: 31px;
}
.beer {
  height: 300px;
  position: absolute;
  top: 120%;
  left: 30%;
  opacity: 0;
  transition: opacity 0.5s;
  transition-delay: 0.3s;
}
.on {
  opacity: 1;
}
</style>