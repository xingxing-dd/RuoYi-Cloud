<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="钱包id" prop="walletId">
        <el-input
          v-model="queryParams.walletId"
          placeholder="请输入钱包id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户号" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关联订单号" prop="bizOrderId">
        <el-input
          v-model="queryParams.bizOrderId"
          placeholder="请输入关联订单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="流水类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择流水类型" clearable>
          <el-option
            v-for="dict in dict.type.wallet_flow_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="方向(+/-)" prop="direct">
        <el-select v-model="queryParams.direct" placeholder="请选择方向(+/-)" clearable>
          <el-option
            v-for="dict in dict.type.wallet_flow_direct"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="变化金额" prop="amount">
        <el-input
          v-model="queryParams.amount"
          placeholder="请输入变化金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="交易币种" prop="currency">
        <el-input
          v-model="queryParams.currency"
          placeholder="请输入交易币种"
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
          v-hasPermi="['client:wallet-flow:add']"
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
          v-hasPermi="['client:wallet-flow:edit']"
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
          v-hasPermi="['client:wallet-flow:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['client:wallet-flow:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="walletFlowList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键id" align="center" prop="id" />
      <el-table-column label="钱包id" align="center" prop="walletId" />
      <el-table-column label="用户号" align="center" prop="userId" />
      <el-table-column label="关联订单号" align="center" prop="bizOrderId" />
      <el-table-column label="流水类型" align="center" prop="type">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.wallet_flow_type" :value="scope.row.type"/>
        </template>
      </el-table-column>
      <el-table-column label="方向(+/-)" align="center" prop="direct">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.wallet_flow_direct" :value="scope.row.direct"/>
        </template>
      </el-table-column>
      <el-table-column label="变化金额" align="center" prop="amount" />
      <el-table-column label="交易币种" align="center" prop="currency" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['client:wallet-flow:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['client:wallet-flow:remove']"
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

    <!-- 添加或修改用户钱包流水对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="钱包id" prop="walletId">
          <el-input v-model="form.walletId" placeholder="请输入钱包id" />
        </el-form-item>
        <el-form-item label="用户号" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户号" />
        </el-form-item>
        <el-form-item label="关联订单号" prop="bizOrderId">
          <el-input v-model="form.bizOrderId" placeholder="请输入关联订单号" />
        </el-form-item>
        <el-form-item label="流水类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择流水类型">
            <el-option
              v-for="dict in dict.type.wallet_flow_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="方向(+/-)" prop="direct">
          <el-select v-model="form.direct" placeholder="请选择方向(+/-)">
            <el-option
              v-for="dict in dict.type.wallet_flow_direct"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="变化金额" prop="amount">
          <el-input v-model="form.amount" placeholder="请输入变化金额" />
        </el-form-item>
        <el-form-item label="交易币种" prop="currency">
          <el-input v-model="form.currency" placeholder="请输入交易币种" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="扩展字段" prop="ext">
          <el-input v-model="form.ext" type="textarea" placeholder="请输入内容" />
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
import { listWalletFlow, getWalletFlow, delWalletFlow, addWalletFlow, updateWalletflow } from "@/api/client/wallet-flow";

export default {
  name: "Wallet-flow",
  dicts: ['wallet_flow_type', 'wallet_flow_direct'],
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
      // 用户钱包流水表格数据
      walletFlowList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        walletId: null,
        userId: null,
        bizOrderId: null,
        type: null,
        direct: null,
        amount: null,
        currency: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        walletId: [
          { required: true, message: "钱包id不能为空", trigger: "blur" }
        ],
        userId: [
          { required: true, message: "用户号不能为空", trigger: "blur" }
        ],
        bizOrderId: [
          { required: true, message: "关联订单号不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "流水类型不能为空", trigger: "change" }
        ],
        direct: [
          { required: true, message: "方向(+/-)不能为空", trigger: "change" }
        ],
        amount: [
          { required: true, message: "变化金额不能为空", trigger: "blur" }
        ],
        currency: [
          { required: true, message: "交易币种不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询用户钱包流水列表 */
    getList() {
      this.loading = true;
      listWalletFlow(this.queryParams).then(response => {
        this.walletFlowList = response.rows;
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
        walletId: null,
        userId: null,
        bizOrderId: null,
        type: null,
        direct: null,
        amount: null,
        currency: null,
        remark: null,
        ext: null,
        delFlag: null,
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
      this.title = "添加用户钱包流水";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getWalletFlow(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改用户钱包流水";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateWalletflow(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWalletFlow(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除用户钱包流水编号为"' + ids + '"的数据项？').then(function() {
        return delWalletFlow(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('client/wallet-flow/export', {
        ...this.queryParams
      }, `wallet-flow_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
