<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="产品编码" prop="productCode">
        <el-input
          v-model="queryParams.productCode"
          placeholder="请输入产品编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['market:config:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['market:config:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['market:config:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['market:config:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="configList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键id" align="center" prop="id" />
      <el-table-column label="产品编码" align="center" prop="productCode" />
      <el-table-column label="交易费率" align="center" prop="feeRate" />
      <el-table-column label="点差率" align="center" prop="spreadRate" />
      <el-table-column label="每张数量" align="center" prop="eachSheetNum" />
      <el-table-column label="最小倍率" align="center" prop="minMultiplier" />
      <el-table-column label="最大倍率" align="center" prop="maxMultiplier" />
      <el-table-column label="倍率步长" align="center" prop="multiplierStepSize" />
      <el-table-column label="最小张数" align="center" prop="minSheetNum" />
      <el-table-column label="最大张数" align="center" prop="maxSheetNum" />
      <el-table-column label="张数步长" align="center" prop="sheetStepSize" />
      <el-table-column label="保证金" align="center" prop="marginValue" />
      <el-table-column label="保证金类型" align="center" prop="marginType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.fee_type" :value="scope.row.marginType"/>
        </template>
      </el-table-column>
      <el-table-column label="价格步长" align="center" prop="priceStepSize" />
      <el-table-column label="价格小数位" align="center" prop="priceUnit" />
      <el-table-column label="开盘时间" align="center" prop="tradePeriod" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['market:config:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['market:config:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改产品配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="产品编码" prop="productCode">
          <el-input v-model="form.productCode" placeholder="请输入产品编码" />
        </el-form-item>
        <el-form-item label="交易费率" prop="feeRate">
          <el-input v-model="form.feeRate" placeholder="请输入交易费率" />
        </el-form-item>
        <el-form-item label="点差率" prop="spreadRate">
          <el-input v-model="form.spreadRate" placeholder="请输入点差率" />
        </el-form-item>
        <el-form-item label="每张数量" prop="eachSheetNum">
          <el-input v-model="form.eachSheetNum" placeholder="请输入每张数量" />
        </el-form-item>
        <el-form-item label="最小倍率" prop="minMultiplier">
          <el-input v-model="form.minMultiplier" placeholder="请输入最小倍率" />
        </el-form-item>
        <el-form-item label="最大倍率" prop="maxMultiplier">
          <el-input v-model="form.maxMultiplier" placeholder="请输入最大倍率" />
        </el-form-item>
        <el-form-item label="倍率步长" prop="multiplierStepSize">
          <el-input v-model="form.multiplierStepSize" placeholder="请输入倍率步长" />
        </el-form-item>
        <el-form-item label="最小张数" prop="minSheetNum">
          <el-input v-model="form.minSheetNum" placeholder="请输入最小张数" />
        </el-form-item>
        <el-form-item label="最大张数" prop="maxSheetNum">
          <el-input v-model="form.maxSheetNum" placeholder="请输入最大张数" />
        </el-form-item>
        <el-form-item label="张数步长" prop="sheetStepSize">
          <el-input v-model="form.sheetStepSize" placeholder="请输入张数步长" />
        </el-form-item>
        <el-form-item label="保证金" prop="marginValue">
          <el-input v-model="form.marginValue" placeholder="请输入保证金" />
        </el-form-item>
        <el-form-item label="保证金类型" prop="marginType">
          <el-select v-model="form.marginType" placeholder="请选择保证金类型">
            <el-option
              v-for="dict in dict.type.fee_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="价格步长" prop="priceStepSize">
          <el-input v-model="form.priceStepSize" placeholder="请输入价格步长" />
        </el-form-item>
        <el-form-item label="价格小数位" prop="priceUnit">
          <el-input v-model="form.priceUnit" placeholder="请输入价格小数位" />
        </el-form-item>
        <el-form-item label="开盘时间" prop="tradePeriod">
          <el-input v-model="form.tradePeriod" type="textarea" placeholder="请输入内容" />
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
import { listConfig, getConfig, delConfig, addConfig, updateConfig } from "@/api/market/config";

export default {
  name: "Config",
  dicts: ['fee_type'],
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
      // 产品配置表格数据
      configList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        productCode: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        productCode: [
          { required: true, message: "产品编码不能为空", trigger: "blur" }
        ],
        feeRate: [
          { required: true, message: "交易费率不能为空", trigger: "blur" }
        ],
        spreadRate: [
          { required: true, message: "点差率不能为空", trigger: "blur" }
        ],
        eachSheetNum: [
          { required: true, message: "每张数量不能为空", trigger: "blur" }
        ],
        minMultiplier: [
          { required: true, message: "最小倍率不能为空", trigger: "blur" }
        ],
        maxMultiplier: [
          { required: true, message: "最大倍率不能为空", trigger: "blur" }
        ],
        multiplierStepSize: [
          { required: true, message: "倍率步长不能为空", trigger: "blur" }
        ],
        minSheetNum: [
          { required: true, message: "最小张数不能为空", trigger: "blur" }
        ],
        maxSheetNum: [
          { required: true, message: "最大张数不能为空", trigger: "blur" }
        ],
        sheetStepSize: [
          { required: true, message: "张数步长不能为空", trigger: "blur" }
        ],
        marginValue: [
          { required: true, message: "保证金不能为空", trigger: "blur" }
        ],
        marginType: [
          { required: true, message: "保证金类型不能为空", trigger: "change" }
        ],
        priceStepSize: [
          { required: true, message: "价格步长不能为空", trigger: "blur" }
        ],
        priceUnit: [
          { required: true, message: "价格小数位不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询产品配置列表 */
    getList() {
      this.loading = true;
      listConfig(this.queryParams).then(response => {
        this.configList = response.rows;
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
        productCode: null,
        feeRate: null,
        spreadRate: null,
        eachSheetNum: null,
        minMultiplier: null,
        maxMultiplier: null,
        multiplierStepSize: null,
        minSheetNum: null,
        maxSheetNum: null,
        sheetStepSize: null,
        marginValue: null,
        marginType: null,
        priceStepSize: null,
        priceUnit: null,
        tradePeriod: null,
        createTime: null,
        createBy: null,
        updateTime: null,
        updateBy: null
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加产品配置";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getConfig(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改产品配置";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateConfig(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addConfig(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除产品配置编号为"' + ids + '"的数据项？').then(function() {
        return delConfig(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('market/config/export', {
        ...this.queryParams
      }, `config_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
