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
      <!--
      <el-form-item label="收货快递ID" prop="logisticsId">
        <el-input
          v-model="queryParams.logisticsId"
          placeholder="请输入收货快递ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      -->
      <el-form-item label="楼栋" prop="apartmentId">
        <el-input
          type="number"
          v-model="queryParams.apartmentId"
          placeholder="请输入楼栋ID"
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
          type="number"
          v-model="queryParams.roomId"
          placeholder="请输入房间ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!--
      <el-form-item label="物品面单图片" prop="img">
        <el-input
          v-model="queryParams.img"
          placeholder="请输入物品面单图片"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="快递公司" prop="courierCompany">
        <el-input
          v-model="queryParams.courierCompany"
          placeholder="请输入快递公司"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="快递单号" prop="courierNumber">
        <el-input
          v-model="queryParams.courierNumber"
          placeholder="请输入快递单号"
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

    <el-row :gutter="10" class="mb8">
      
    <!--
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['logistics:items:add']"
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
          v-hasPermi="['logistics:items:edit']"
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
          v-hasPermi="['logistics:items:remove']"
          >删除</el-button
        >
      </el-col>
      -->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['logistics:items:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="itemsList"
      @selection-change="handleSelectionChange"
    >
      <!--
      <el-table-column type="selection" width="55" align="center" />
      -->
      <el-table-column label="ID" align="center" prop="id" width="100"/>
      <!--
      <el-table-column label="收货快递ID" align="center" prop="logisticsId" />
      -->
      <el-table-column label="楼栋ID" align="center" prop="apartmentId" width="100"/>
      <!--
      <el-table-column label="单元" align="center" prop="unitId" />
      -->
      <el-table-column label="房间ID" align="center" prop="roomId" width="100"/>
      <el-table-column label="物品面单图片" align="center" prop="img" width="150">
      <template slot-scope="scope">
        <img :src="scope.row.img" style="width:150px;height:150px"/>
      </template>
      </el-table-column>      
      <el-table-column label="配件" align="center" prop="createTime" width="150">
      <template slot-scope="scope">
        <div>{{scope.row.createBy}}</div>
        <div>{{scope.row.createTime}}</div>
      </template>
      </el-table-column>      
      <el-table-column label="送件" align="center" prop="updateTime" width="150">
      <template slot-scope="scope">
        <div>{{scope.row.updateBy}}</div>
        <div>{{scope.row.updateTime}}</div>
      </template>
      </el-table-column>
      <!--
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="快递公司" align="center" prop="courierCompany" />
      <el-table-column label="快递单号" align="center" prop="courierNumber" />
      -->
      <el-table-column label="备注" align="center" prop="remark" />
      <!--
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['logistics:items:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['logistics:items:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
      -->
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改快件信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="收货快递ID" prop="logisticsId">
          <el-input v-model="form.logisticsId" placeholder="请输入收货快递ID" />
        </el-form-item>
        <el-form-item label="楼栋ID" prop="apartmentId">
          <el-input v-model="form.apartmentId" placeholder="请输入楼栋ID" />
        </el-form-item>
        <el-form-item label="单元" prop="unitId">
          <el-input v-model="form.unitId" placeholder="请输入单元" />
        </el-form-item>
        <el-form-item label="房间ID" prop="roomId">
          <el-input v-model="form.roomId" placeholder="请输入房间ID" />
        </el-form-item>
        <el-form-item label="物品面单图片" prop="img">
          <el-input v-model="form.img" placeholder="请输入物品面单图片" />
        </el-form-item>
        <el-form-item label="快递公司" prop="courierCompany">
          <el-input
            v-model="form.courierCompany"
            placeholder="请输入快递公司"
          />
        </el-form-item>
        <el-form-item label="快递单号" prop="courierNumber">
          <el-input v-model="form.courierNumber" placeholder="请输入快递单号" />
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
  listItems,
  getItems,
  delItems,
  addItems,
  updateItems,
} from "@/api/logistics/items";

export default {
  name: "Items",
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
      // 快件信息表格数据
      itemsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        logisticsId: null,
        apartmentId: null,
        unitId: null,
        roomId: null,
        img: null,
        status: null,
        courierCompany: null,
        courierNumber: null,
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
    /** 查询快件信息列表 */
    getList() {
      this.loading = true;
      let logistics_id=this.$route.query.id
      if(logistics_id && logistics_id>0 && this.queryParams.roomId!=null && this.queryParams.apartmentId!=null){
        this.queryParams.logisticsId=logistics_id
      }
      listItems(this.queryParams).then((response) => {
        this.itemsList = response.rows;
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
        logisticsId: null,
        apartmentId: null,
        unitId: null,
        roomId: null,
        img: null,
        status: "0",
        courierCompany: null,
        courierNumber: null,
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
      this.title = "添加快件信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getItems(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改快件信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateItems(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addItems(this.form).then((response) => {
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
        .confirm('是否确认删除快件信息编号为"' + ids + '"的数据项？')
        .then(function () {
          return delItems(ids);
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
        "logistics/items/export",
        {
          ...this.queryParams,
        },
        `items_${new Date().getTime()}.xlsx`
      );
    },
  },
};
</script>
