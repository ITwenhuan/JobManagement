<template>
    <div>
        <div style="margin-bottom: 20px">
            <el-button type="primary" icon="el-icon-plus" @click="addJobInfo">新增</el-button>
        </div>
        <el-table
            :data="tableData"
            style="width: 100%">
            <el-table-column
                fixed
                prop="autoReportTitle"
                label="报告标题"
                width="150">
            </el-table-column>
            <el-table-column
                prop="autoReportCreatorShow"
                label="创建人"
                width="150">
            </el-table-column>
            <el-table-column
                prop="createdStimeShow"
                label="创建时间"
                width="180">
            </el-table-column>
            <el-table-column
                prop="autoReportLastExecuteTimeShow"
                label="最新执行时间"
                width="180">
            </el-table-column>
            <el-table-column
                prop="autoReportCron"
                label="执行策略"
                width="200">
            </el-table-column>
            <el-table-column
                prop="autoReportStatus"
                label="当前状态"
                width="120">
                <template slot-scope="scope">
                    <el-switch v-model="scope.row.autoReportStatus" :active-value="1" :inactive-value="2" active-color="#13ce66" inactive-color="#ff4949" @change="handleSwitch(scope.row)"></el-switch>
                </template>
            </el-table-column>
            <el-table-column
                fixed="right"
                label="操作">
                <template slot-scope="scope">
                    <el-button-group>
                        <el-button type="primary" icon="el-icon-edit" @click="editJobInfo(scope.row)">编辑</el-button>
                        <el-button type="primary" icon="el-icon-delete" @click="del(scope.row.id)">删除</el-button>
                        <el-button type="primary" icon="el-icon-video-play" @click="manual(scope.row.id)">立即执行</el-button>
                    </el-button-group>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog
            title="添加/修改自动化报告任务"
            :visible.sync="dialogVisible"
            width="750px" :before-close="closeDialog">
            <el-form :model="jobInfo" ref="jobInfo">
                <el-form-item label="id" prop="id" v-show="false">
                    <el-input v-model="jobInfo.id" type="hidden"></el-input>
                </el-form-item>
                <el-form-item label="邮件标题:" :label-width="formLabelWidth">
                    <el-input v-model.trim="jobInfo.autoReportTitle" placeholder="请输入内容"></el-input>
                </el-form-item>
                <el-form-item label="邮件收件人:" :label-width="formLabelWidth">
                    <!--<el-checkbox v-model="jobInfo.autoReportToTmp" :items="items"></el-checkbox>-->
                    <el-input v-model.trim="jobInfo.autoReportToOther"></el-input>
                </el-form-item>
                <el-form-item label="邮件抄送人:" :label-width="formLabelWidth">
                    <!--<el-checkbox v-model="jobInfo.autoReportCcTmp" :items="items"></el-checkbox>-->
                    <el-input v-model.trim="jobInfo.autoReportCcOther"></el-input>
                </el-form-item>
                <el-form-item label="邮件发送人:" :label-width="formLabelWidth">
                    <el-input v-model.trim="jobInfo.autoReportFrom" placeholder="请输入内容"></el-input>
                </el-form-item>
                <el-form-item label="邮件正文模板:" :label-width="formLabelWidth">
                    <!--<el-radio v-model="jobInfo.autoReportTemplate" :items="templates">
                    </el-radio>-->
                </el-form-item>
                <el-form-item label="数据范围:" :label-width="formLabelWidth">
                    <el-input v-model="jobInfo.autoReportData"
                                 :type="'textarea'"
                                 :rows="5" :disabled="isDisable"></el-input>
                </el-form-item>
                <el-form-item label="发送策略:" :label-width="formLabelWidth">
                    <el-popover v-model="cronPopover">
                        <cron @change="changeCron" @close="cronPopover=false" i18n="cn"></cron>
                        <el-input slot="reference" @click="cronPopover=true" v-model="jobInfo.autoReportCron" placeholder="请输入定时策略" class="cron"></el-input>
                    </el-popover>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="closeDialog">取 消</el-button>
                <el-button type="primary" @click="saveJob">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    import {cron} from "vue-cron";
    import {jobCenterService} from '@/api/jobManage'

    export default {
        name: 'jobList',
        components: { cron },
        data() {
            return {
                cronPopover:false,
                cron:'',
                dialogVisible: false,
                isDisable: false,
                formLabelWidth: '100px',
                jobInfo: {
                    id: '',
                    autoReportTitle: '',
                    autoReportFrom: '',
                    autoReportTo: '',
                    autoReportCc: '',
                    autoReportToTmp: [],
                    autoReportCcTmp: [],
                    autoReportTemplate: '',
                    autoReportData: '',
                    autoReportCron: '',
                    autoReportToOther: '',
                    autoReportCcOther: ''
                },
                tableData: []
            }
        },
        methods: {
            changeCron(val){
                this.jobInfo.autoReportCron = val
            },
            initOpt() {
                jobCenterService.getJobList().then(res => {
                    if (res.data.code == 200 && res.data.status == 1) {
                        this.tableData = res.data.returnObject;
                    } else {
                        this.$notify({
                            title: "提示",
                            message: "列表查询失败",
                            type: "warning"
                        });
                    }
                })
            },
            handleSwitch(item) {
                jobCenterService.updateJobStatus({id: item.id,autoReportStatus:item.autoReportStatus}).then(res => {
                    if (res.data.code == 200 && res.data.status == 1 && res.data.returnObject) {
                        this.$notify({
                            title: "提示",
                            message: "成功",
                            type: "success"
                        });
                    } else {
                        this.$notify({
                            title: "提示",
                            message: "失败",
                            type: "warning"
                        });
                    }
                })
            },
            del(item) {
                console.info("删除任务：" + item);
                jobCenterService.delJob({id: item}).then(res => {
                    if (res.data.code == 200 && res.data.status == 1) {
                        this.$notify({
                            title: "提示",
                            message: "成功",
                            type: "success"
                        });
                        setTimeout(function () {
                            this.reload();
                        }, 1000);
                    } else {
                        this.$notify({
                            title: "提示",
                            message: "失败",
                            type: "warning"
                        });
                    }
                })
            },
            /*立即执行*/
            manual(item) {
                console.info("立即执行任务：" + item);
                jobCenterService.manualJob({id: item}).then(res => {
                    if (res.data.code == 200 && res.data.status == 1) {
                        this.$notify({
                            title: "提示",
                            message: "成功",
                            type: "success"
                        });
                    } else {
                        this.$notify({
                            title: "提示",
                            message: "失败",
                            type: "warning"
                        });
                    }
                })
            },
            addJobInfo() {
                this.dialogVisible = true;
            },
            editJobInfo(row) {
                let $data = Object.assign({}, row);

                if ($data.autoReportCc != undefined && $data.autoReportCc != "") {
                    $data.autoReportCcTmp = $data.autoReportCc.split(",").map(Number);
                } else {
                    $data.autoReportCcTmp = []
                }
                if ($data.autoReportTo != undefined && $data.autoReportTo != "") {
                    $data.autoReportToTmp = $data.autoReportTo.split(",").map(Number);
                } else {
                    $data.autoReportToTmp = []
                }
                this.$nextTick(()=>{
                    this.jobInfo = $data;
                });
                this.dialogVisible = true;
                this.isDisable = true;
            },
            closeDialog(){
                this.dialogVisible = false;
                this.isDisable = false;
                this.jobInfo.id = '';
                this.jobInfo.autoReportTitle = '';
                this.jobInfo.autoReportToTmp = [];
                this.jobInfo.autoReportCcTmp = [];
                this.jobInfo.autoReportTo = '';
                this.jobInfo.autoReportCc = '';
                this.jobInfo.autoReportTemplate = '';
                this.jobInfo.autoReportData = '';
                this.jobInfo.autoReportCron = '';
                this.jobInfo.autoReportToOther = '';
                this.jobInfo.autoReportCcOther = '';
                this.initOpt();
            },
            saveJob() {
                console.info("保存任务:" + JSON.stringify(this.jobInfo));
                let $this = this;
                this.jobInfo.autoReportTo = this.jobInfo.autoReportToTmp.join(",");
                this.jobInfo.autoReportCc = this.jobInfo.autoReportCcTmp.join(",");
                jobCenterService.saveJob(this.jobInfo).then(res => {
                    if (res.data.code == 200 && res.data.status == 1) {
                        this.dialogVisible = false;
                        this.isDisable = false;
                        this.$notify({
                            title: "提示",
                            message: "成功",
                            type: "success"
                        });
                        setTimeout(function () {
                            $this.reload();
                        }, 1000);
                    } else {
                        this.$notify({
                            title: "提示",
                            message: "失败",
                            type: "warning"
                        });
                    }
                })
            }
        },
        mounted() {
            this.initOpt();
        },
        created() {
        },
    }
</script>
