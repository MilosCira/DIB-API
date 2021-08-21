<template>
  <div class="beer">
    <div class="home">
      <router-link to="/"><i class="fas fa-home"></i></router-link>
      <i @click="addAlert = true" class="fas fa-plus"></i>
    </div>
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th class="desc">Description</th>
          <th>Temperature</th>
          <th>See detail</th>
          <th>Edit</th>
          <th>Delete</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="bee in data" :key="bee.id">
          <td>{{ bee.id }}</td>
          <td>
            <h3>{{ bee.name }}</h3>
          </td>
          <td class="desc">{{ bee.description }}</td>
          <td class="temperature">{{ bee.temperature }}C&#176;</td>
          <td class="temperature click" @click="aboutBeer(bee.id)">
            <i class="fas fa-eye"></i>
          </td>
          <td><i @click="getInfo(bee.id)" class="far fa-edit click"></i></td>
          <td class="temperature click" @click="deleteBeer(bee.id)">
            <i class="fas fa-trash-alt"></i>
          </td>
        </tr>
      </tbody>
    </table>
    <transition name="fade" appear>
      <div
        class="modal-overlay"
        v-if="showAlert"
        @click="showAlert = false"
      ></div>
    </transition>
    <transition name="slide" appear>
      <div class="modal" v-if="showAlert">
        <h2>About beer</h2>
        <h3>{{ alertName }}</h3>
        <p class="alertDesc">{{ alertDescription }}</p>
        <h3>Temperature: {{ alertTemperature }} C&#176;</h3>
        <button class="cancelButton" @click="showAlert = false">Cancel</button>
      </div>
    </transition>

    <transition name="fade" appear>
      <div
        class="modal-overlay"
        v-if="addAlert"
        @click="addAlert = false"
      ></div>
    </transition>
    <transition name="slide" appear>
      <div class="modal" v-if="addAlert">
        <h2>Add beer</h2>
        <input type="text" v-model="nameInput" />
        <input type="text" v-model="decriptionInput" />
        <input type="text" v-model="temperatureInput" />
        <button class="buttonModal" @click="addBeer">Add beer</button>
        <button class="cancelButton" @click="addAlert = false">Cancel</button>
      </div>
    </transition>

    <!-- Edit modal -->
    <transition name="fade" appear>
      <div
        class="modal-overlay"
        @click="editBeer = false"
        v-if="editBeer"
      ></div>
    </transition>
    <transition name="slide" appear>
      <div class="modal" v-if="editBeer">
        <input type="text" v-model="name" />
        <input type="text" v-model="desc" />
        <input type="text" v-model="temp" />
        <button class="confirm" @click="editBeers">Edit beer</button>
        <button class="cancel" @click="editBeer = false">Cancel</button>
      </div>
    </transition>
  </div>
</template>

<script>
// @ is an alias to /src

import axios from "axios";
export default {
  data() {
    return {
      data: "",
      baseURL: "http://localhost:8090",
      showAlert: false,
      alertName: "",
      alertDescription: "",
      alertTemperature: "",
      addAlert: false,
      nameInput: "",
      decriptionInput: "",
      temperatureInput: "",
      inputId: "",
      editBeer: false,
      name: "",
      temp: "",
      desc: "",
    };
  },
  methods: {
    getBeers() {
      axios.get(this.baseURL + "/api/beers").then((res) => {
        console.log(res);

        this.data = res.data;
      });
    },
    getInfo(id) {
      this.editBeer = true;
      axios.get(this.baseURL + "/api/beers/" + id, {}).then((res) => {
        console.log(res);
        this.name = res.data.name;
        this.desc = res.data.description;
        this.temp = res.data.temperature;
      });
    },
    deleteBeer(id) {
      axios.delete(this.baseURL + "/api/beers/" + id, {}).then((res) => {
        console.log(res);
        this.$toast.success("successfully delete beer", {
          duration: 3000,
        });
        this.getBeers();
      });
    },
    aboutBeer(id) {
      this.showAlert = true;
      axios.get(this.baseURL + "/api/beers/" + id, {}).then((res) => {
        console.log(res);
        this.alertName = res.data.name;
        this.alertDescription = res.data.description;
        this.alertTemperature = res.data.temperature;
      });
    },
    addBeer() {
      this.addAlert = true;
      axios
        .post(this.baseURL + "/api/beers", {
          description: this.decriptionInput,
          name: this.nameInput,
          temperature: this.temperatureInput,
        })
        .then((res) => {
          console.log(res);
          this.$toast.success("successfully add a beer", {
            duration: 3000,
          });
        });
    },

    editBeers() {
      axios
        .put(
          this.baseURL + "/api/beers", {
            name:this.name,
            description:this.desc,
            temperature:this.temp,
            
          },
          
          )
        .then((res) => {
          console.log(res);
          this.getBeers();
          this.$toast.success("successfully edit beer", {
            duration: 3000,
          });
          this.editBeer=false;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  mounted() {
    this.getBeers();
  },
};
</script>

<style scoped>
.modal .confirm {
  padding: 10px 20px;
  background-color: #00986f;

  color: #fff;
  font-weight: bold;
  font-size: 16px;
  border-radius: 3px;
  margin: 10px 0;
  cursor: pointer;
}
.modal .cancel {
  padding: 10px 20px;
  border: 2px solid #ff0000;
  color: #fff;
  background-color: #ff0000;
  font-weight: bold;
  font-size: 16px;
  border-radius: 3px;
  margin: 0 0 10px;
  cursor: pointer;
}
.beer {
  min-height: 100vh;
  background-color: #1d2026;
  padding: 40px 0 0 0;
}
.desc {
  height: 20px;
  overflow: auto;
}
table {
  width: 1200px;
  border-collapse: collapse;
  margin: auto;
}
.home i {
  font-size: 40px;
  color: white;
  margin: 0 auto;
  margin-left: 15px;
  margin-bottom: 8px;
}
/* Zebra striping */
tr:nth-of-type(odd) {
  background: #eee;
}
/* Zebra striping */
tr:nth-of-type(even) {
  background: rgb(173, 173, 173);
}
tr {
  border-top-left-radius: 25px;
  border-top-right-radius: 25px;
}
tr th {
  background: #00986f;
  color: white;
  font-weight: bold;
  text-align: center;
}

td,
th {
  padding: 10px;
  border: 1px solid #ccc;
  text-align: left;
  font-size: 18px;
}
.temperature {
  text-align: center;
}

.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 5;
  background-color: rgba(0, 0, 0, 0.3);
}
.modal {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 99;
  width: 50%;
  max-width: 85%;
  background-color: #fff;
  border-radius: 16px;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 25px;
}
.buttonModal {
  appearance: none;
  outline: none;
  border: none;
  background: none;
  cursor: pointer;
  display: inline-block;
  padding: 15px 25px;
  background-color: #7af15c;
  border-radius: 8px;
  color: rgb(0, 0, 0);
  font-size: 18px;
  font-weight: 700;
  box-shadow: 3px 3px rgba(0, 0, 0, 0.4);
  transition: 0.4s ease-out;
  margin-top: 20px;
  height: 50px;
  width: auto;
}
.closeBtn {
  background-image: linear-gradient(100deg, #fa2c2c 0%, #e94b4b 74%);
}
.modal h2 {
  color: #222;
  font-size: 32px;
  font-weight: 900;
  margin-bottom: 15px;
}
.modal p {
  color: #666;
  font-size: 18px;
  font-weight: 400;
  margin-bottom: 15px;
}
.modal input {
  margin-top: 10px;
  border-radius: 5px;
  border: none;
  background-color: #bbbbbb;
  /*  margin: 10px; */
  height: 50px;
  width: 300px;
  padding: 0 25px;
  font-size: 15px;
  animation: scale 0.1s linear;
  animation-fill-mode: forwards;
}
.alertDesc {
  margin: 20px auto;
  text-align: justify;
}
.deleteButton,
.cancelButton {
  appearance: none;
  outline: none;
  border: none;
  background: none;
  cursor: pointer;
  display: inline-block;
  padding: 15px 25px;
  border-radius: 8px;
  color: rgb(0, 0, 0);
  font-size: 18px;
  font-weight: 700;
  box-shadow: 3px 3px rgba(0, 0, 0, 0.4);
  transition: 0.4s ease-out;
  margin-top: 20px;
  height: 100%;
  width: auto;
}
.deleteButton:hover {
  z-index: 100;
  animation: scaledown 0.1s linear;
  animation-fill-mode: forwards;
}
.deleteButton {
  background-image: linear-gradient(100deg, #fa2c2c 0%, #e94b4b 70%);
  color: #fff;
}
.cancelButton {
  background-color: rgb(168, 168, 168);
}
.cancelButton:hover {
  z-index: 100;
  animation: scaledown 0.1s linear;
  animation-fill-mode: forwards;
}
.fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter,
.fade-leave-to {
  opacity: 0;
}
.slide-enter-active,
.slide-leave-active {
  transition: transform 0.5s;
}
.slide-enter,
.slide-leave-to {
  transform: translateY(-50%) translateX(100vw);
}

/* 
Max width before this PARTICULAR table gets nasty
This query will take effect for any screen smaller than 760px
and also iPads specifically.
*/
@media only screen and (max-width: 760px),
  (min-device-width: 768px) and (max-device-width: 1024px) {
  table {
    width: 100%;
  }

  /* Force table to not be like tables anymore */
  table,
  thead,
  tbody,
  th,
  td,
  tr {
    display: block;
  }

  /* Hide table headers (but not display: none;, for accessibility) */
  thead tr {
    position: absolute;
    top: -9999px;
    left: -9999px;
  }

  tr {
    border: 1px solid #ccc;
  }

  td {
    /* Behave  like a "row" */
    border: none;
    border-bottom: 1px solid #eee;
    position: relative;
    padding-left: 50%;
  }

  td:before {
    /* Now like a table header */
    position: absolute;
    /* Top/left values mimic padding */
    top: 6px;
    left: 6px;
    width: 45%;
    padding-right: 10px;
    white-space: nowrap;
    /* Label the data */
    content: attr(data-column);

    color: #000;
    font-weight: bold;
  }
}
</style>
