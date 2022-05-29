<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="楼栋" prop="apartmentId">
        <el-input
          type="number"
          v-model="queryParams.apartmentId"
          placeholder="请输入楼栋"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!--
      <el-form-item label="单元" prop="unitId">
        <el-input
          v-model="queryParams.unitId"
          placeholder="请输入单元"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      -->
      <el-form-item label="房间" prop="roomId">
        <el-input
          v-model="queryParams.roomId"
          placeholder="请输入房间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!--
      <el-form-item label="门牌图片" prop="roomImg">
        <el-input
          v-model="queryParams.roomImg"
          placeholder="请输入门牌图片"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="租户名" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入租户名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="租户手机" prop="userPhone">
        <el-input
          v-model="queryParams.userPhone"
          placeholder="请输入租户手机"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      -->
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>

    <!--
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['logistics:logistics:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['logistics:logistics:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['logistics:logistics:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['logistics:logistics:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>
    -->

    <el-table
      v-loading="loading"
      :data="logisticsList"
      @selection-change="handleSelectionChange"
    >
      <!--
      <el-table-column type="selection" width="55" align="center" />
      -->
      <el-table-column label="ID" align="center" prop="id" width="100" />
      <el-table-column
        label="楼栋ID"
        align="center"
        prop="apartmentId"
        width="100"
      />
      <!--
      <el-table-column label="单元" align="center" prop="unitId" />
      -->
      <el-table-column
        label="房间ID"
        align="center"
        prop="roomId"
        width="100"
      />
      <!--
      <el-table-column
        label="门牌图片"
        align="center"
        prop="roomImg"
        width="150"
      >
        <template slot-scope="scope">
          <img :src="scope.row.roomImg" style="width: 150px; height: 150px" />
        </template>
      </el-table-column>
      -->
      <el-table-column
        label="日期"
        align="center"
        prop="createTime"
        width="150"
      >
        <template slot-scope="scope">
          {{ scope.row.createTime }}
        </template>
      </el-table-column>
      <!--
      <el-table-column label="状态" align="center" prop="status" />
      
      <el-table-column label="租户名" align="center" prop="userName" />
      <el-table-column label="租户手机" align="center" prop="userPhone" />
      -->
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
        width="120"
      >
        <template slot-scope="scope">
          <!--
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['logistics:logistics:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['logistics:logistics:remove']"
            >删除</el-button
          >
          -->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleShowDetail(scope.row)"
            >快件明细</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改物流信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="楼栋ID" prop="apartmentId">
          <el-input v-model="form.apartmentId" placeholder="请输入楼栋ID" />
        </el-form-item>
        <el-form-item label="单元" prop="unitId">
          <el-input v-model="form.unitId" placeholder="请输入单元" />
        </el-form-item>
        <el-form-item label="房间ID" prop="roomId">
          <el-input v-model="form.roomId" placeholder="请输入房间ID" />
        </el-form-item>
        <el-form-item label="门牌图片" prop="roomImg">
          <el-input v-model="form.roomImg" placeholder="请输入门牌图片" />
        </el-form-item>
        <el-form-item label="租户名" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入租户名" />
        </el-form-item>
        <el-form-item label="租户手机" prop="userPhone">
          <el-input v-model="form.userPhone" placeholder="请输入租户手机" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input
            v-model="form.remark"
            type="textarea"
            placeholder="请输入内容"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listLogistics,
  getLogistics,
  delLogistics,
  addLogistics,
  updateLogistics,
} from "@/api/logistics/logistics";

export default {
  name: "Logistics",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 物流信息表格数据
      logisticsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        apartmentId: null,
        unitId: null,
        roomId: null,
        roomImg: null,
        status: null,
        userName: null,
        userPhone: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" },
        ],
        updateTime: [
          { required: true, message: "更新时间不能为空", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询物流信息列表 */
    getList() {
      this.loading = true;
      listLogistics(this.queryParams).then((response) => {
        this.logisticsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        apartmentId: null,
        unitId: null,
        roomId: null,
        roomImg: null,
        status: "0",
        userName: null,
        userPhone: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加物流信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getLogistics(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改物流信息";
      });
    },
    handleShowDetail(row) {
      const id = row.id;
      this.$router.push({
        path: "/logistics/items",
        query: {
          id
        },
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateLogistics(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addLogistics(this.form).then((response) => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal
        .confirm('是否确认删除物流信息编号为"' + ids + '"的数据项？')
        .then(function () {
          return delLogistics(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "logistics/logistics/export",
        {
          ...this.queryParams,
        },
        `logistics_${new Date().getTime()}.xlsx`
      );
    },
  },
};
</script>
