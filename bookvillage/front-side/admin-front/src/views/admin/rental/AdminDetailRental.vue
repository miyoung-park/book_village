<template>
  <div class="content">
    <div class="table_section">
      <v-data-table
          :headers="headers"
          :items="rentalList"
          :items-per-page="5"
          sort-by="rentalSeq"
          :sort-desc= true
          primary-key="index"
      >
        <template v-slot:item="{item}" >
          <tr>
            <td>{{rentalList.indexOf(item) + 1}}</td>
            <td>{{item.bookSeq}}</td>
            <td>{{item.bookTitle}}</td>
            <td>{{item.rentalDt}}</td>
            <td>{{item.predictReturnDt}}</td>
            <td>{{item.returnDt}}</td>
            <td>{{getRentalStatus(item.rentalStatus)}}</td>
            <td>{{item.rentalRegDt}}</td>
            <td>{{item.rentalUpdateDt}}</td>
          </tr>
        </template>
      </v-data-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "AdminDetailRental",
  inject: ['rentalService'],
  data () {
    return {
      search: '',
      userSeq: null,
      headers: [
        { text: '번호', align: 'start' },
        { text: '도서번호', value: 'bookSeq'},
        { text: '도서명', value: 'bookTitle'},
        { text: '대여일', value: 'rentalDt' },
        { text: '반납예정일', value: 'predictReturnDt' },
        { text: '반납일', value: 'returnDt' },
        { text: '대여현황', value: 'rentalStatus' },
        { text: '등록날짜', value: 'rentalRegDt' },
        { text: '수정날짜', value: 'rentalUpdateDt' }
      ],
      rentalList: [],
    }
  },
  created() {
    this.userSeq  = this.$route.params.userSeq;
  },
  methods: {
    async getRentalList(){
      const response = await this.rentalService.getRentalListBySeq(this.userSeq);
      this.rentalList = response;
    },
    getRentalStatus( status ){
      if( status === '00'){
        return "대여신청중";
      } else if( status === '01') {
        return "대여中";
      } else if( status === '02') {
        return "반납완료";
      } else if( status === '03') {
        return "연체";
      } else if( status === '04') {
        return "대여취소";
      }
    }
  },
  mounted() {
    this.getRentalList();
  }
}
</script>

<style scoped>
.content{
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.table_section {
  width: 80%;
  height: 100%;
  margin-top: 50px;
}
</style>