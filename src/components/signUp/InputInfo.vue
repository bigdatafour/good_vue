<template>
  <div class="info-form">
    <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80" >
      <FormItem label="用户名" prop="user_name">
        <i-input v-model="formValidate.user_name" clearable size="large" placeholder="请输入你的姓名"></i-input>
      </FormItem>
      <FormItem label="邮箱" prop="user_mail">
        <i-input v-model="formValidate.user_mail" clearable size="large" placeholder="请输入你的邮箱"></i-input>
      </FormItem>
      <FormItem label="密码" prop="user_password">
        <i-input type="password" v-model="formValidate.user_password" clearable size="large" placeholder="请输入你的密码"></i-input>
      </FormItem>
      <FormItem label="确认密码" prop="repassword">
        <i-input type="password" v-model="formValidate.repassword" clearable size="large" placeholder="请再次输入你的密码"></i-input>
      </FormItem>
      <Button type="error" size="large" long @click="Submit('formValidate')">注册</Button>
    </Form>
  </div>
</template>

<script>
    import store from '@/vuex/store';
    import { mapMutations, mapActions } from 'vuex';
    import axios from 'axios'
    export default {
        name: 'InputInfo',
        data () {
            const validatePassCheck = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.formValidate.user_password) {
                    callback(new Error('两次输入的密码不一样'));
                } else {
                    callback();
                }
            };
            return {
                formValidate: {
                    user_name: '',
                    user_password: '',
                    repassword:'',
                    user_phone: this.$route.query.phone,
                    user_address:'',
                    user_mail: '',
                    user_login_status: 1
                },
                ruleValidate: {
                    user_name: [
                        { required: true, message: '用户名不能为空', trigger: 'blur' }
                    ],
                    user_mail: [
                        { required: true, message: '邮箱不能为空', trigger: 'blur' },
                        { type: 'email', message: '邮箱格式错误', trigger: 'blur' }
                    ],
                    user_password: [
                        { required: true, message: '密码不能为空', trigger: 'blur' },
                        { type: 'string', min: 6, message: '密码长度不能小于6', trigger: 'blur' }
                    ],
                    repassword: [
                        { validator: validatePassCheck, trigger: 'blur' }
                    ]
                }
            };
        },
        methods: {

            /*...mapMutations(['SET_SIGN_UP_SETP']),
            ...mapActions(['addSignUpUser']),
            handleSubmit (name) {
                const father = this;
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        this.$Message.success('注册成功');
                        const userinfo = {
                            username: this.formValidate.name,
                            password: this.formValidate.password,
                            mail: this.formValidate.mail,
                            user_phone: this.$route.query.phone
                        };
                        this.addSignUpUser(userinfo);
                        father.SET_SIGN_UP_SETP(2);
                        this.$router.push({ path: '/SignUp/signUpDone' });
                    } else {
                        this.$Message.error('注册失败');
                    }
                });
            }*/
            Submit() {
                if(this.formValidate.user_name !== '' & this.formValidate.user_password !== '' & this.formValidate.user_mail !== '' & this.formValidate.repassword !== '') {
                    const formData = JSON.stringify(this.formValidate);
                    axios({
                        method:"post",
                        url:"http://localhost:8082/shopOnline/registerUser",
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        data:formData
                    }).then((res)=>{
                        if(res.data==true){
                            this.$Message.success("注册成功");
                            this.$router.push({ path: '/SignUp/signUpDone' });
                            console.log(res);
                        }else{
                            this.$Message.error("用户名已存在,注册失败");
                        }
                    });
                }else{
                    this.$Message.error("请补充您的注册基本信息");
                }
            }
        }
    };
</script>

<style scoped>
  .info-form {
    width: 90% !important;
  }
</style>
