<template>
  <div class="beer">
    <div class="home">
      
    <router-link to="/"><i class="fas fa-home"></i></router-link>
    </div>
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th class="desc">Description</th>
          <th>Temperature</th>
          <th>See detail for one beer</th>
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
          <td class="temperature click"><i class="fas fa-eye"></i></td>
          <td class="temperature click" @click="deleteBeer(bee.id)">
            <i class="fas fa-trash-alt"></i>
          </td>
        </tr>
      </tbody>
    </table>
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
    };
  },
  methods: {
    getBeers() {
      axios.get(this.baseURL + "/api/beers").then((res) => {
        console.log(res);
        this.data = res.data;
      });
    },
    deleteBeer(id) {
      axios.delete(this.baseURL + "/api/beers/" + id, {}).then((res) => {
        console.log(res);
        this.$toast.success("Success delete beer", {
          duration: 3000,
        });
        this.getBeers();
      });
    },
  },
  mounted() {
    this.getBeers();
  },
};
</script>

<style scoped>
.beer {
  min-height: 100vh;
  background-color: #1d2026;
  padding: 40px 0 0 0;
}
.desc{
  height: 20px;
  overflow: auto;
}
table {
  width: 1200px;
  border-collapse: collapse;
  margin: auto;
}
.home i{
  font-size: 50px;
  color: white;
  margin: 0 auto;
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
