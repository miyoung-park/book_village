import $axiosInst from '@/axios/AxiosInst'

export class CustomerService {

    constructor(host) {
        this.host = host;
    }

    getCustomerList(){
        return $axiosInst
            .get(`${this.host}/user/list`)
            .then( response => {
                return response.data;
            }).catch( error => {
                console.log(error);
            })
    }


    addCustomer(customerObj){
        return $axiosInst
            .post(`${this.host}/user/add` , customerObj)
            .then( response => {
                return response.data;
            }).catch( error => {
                console.log(error);
            })
    }

    getCustomerDetail(userSeq) {
        return $axiosInst
            .get(`${this.host}/user/detail/` + userSeq)
            .then(response => {
                return response.data;
            }).catch(error => {
                console.log(error);
            })
    }

    updateCustomer(customerObj) {
        const userSeq = customerObj.userSeq
        return $axiosInst
            .put(`${this.host}/user/update/` + userSeq , customerObj)
            .then( () => {
                return;
            }).catch( error => {
                console.log(error);
            })
    }

    deleteCustomer(userSeq){
        return $axiosInst
            .delete(`${this.host}/user/delete/` + userSeq)
            .then( () => {
                return;
            }).catch( error => {
                console.log(error);
            })
    }



}