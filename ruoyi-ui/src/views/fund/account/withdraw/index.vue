<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="账号" prop="accountNo">
        <el-input
          v-model="queryParams.accountNo"
          placeholder="请输入账号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="币种" prop="accountCurrency">
        <el-input
          v-model="queryParams.accountCurrency"
          placeholder="请输入币种"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="账户类型" prop="accountType">
        <el-select v-model="queryParams.accountType" placeholder="请选择账户类型" clearable>
          <el-option
            v-for="dict in dict.type.fund_account_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="账号机构号码" prop="accountOwnerCode">
        <el-input
          v-model="queryParams.accountOwnerCode"
          placeholder="请输入账号机构号码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in dict.type.sys_data_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['fund:account:add']"
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
          v-hasPermi="['fund:account:edit']"
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
          v-hasPermi="['fund:account:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['fund:account:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="accountList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="编号" align="center" prop="id" />-->
      <el-table-column label="账户名" align="center" prop="accountName" />
      <el-table-column label="账号" align="center" prop="accountNo" />
      <el-table-column label="币种" align="center" prop="accountCurrency" />
      <el-table-column label="账户类型" align="center" prop="accountType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.fund_account_type" :value="scope.row.accountType"/>
        </template>
      </el-table-column>
      <el-table-column label="账户用途" align="center" prop="accountUsage">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.fund_account_usage" :value="scope.row.accountUsage"/>
        </template>
      </el-table-column>
      <el-table-column label="账户所属机构" align="center" prop="accountOwner" />
      <el-table-column label="账号机构号码" align="center" prop="accountOwnerCode" />
      <el-table-column label="账号机构地址" align="center" prop="accountOwnerAddr" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_data_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['fund:account:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['fund:account:remove']"
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

    <!-- 添加或修改充值帐号对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="账户名" prop="accountName">
          <el-input v-model="form.accountName" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="账号" prop="accountNo">
          <el-input v-model="form.accountNo" placeholder="请输入账号" />
        </el-form-item>
        <el-form-item label="币种" prop="accountCurrency">
          <el-input v-model="form.accountCurrency" placeholder="请输入币种" />
        </el-form-item>
        <el-form-item label="账户类型" prop="accountType">
          <el-select v-model="form.accountType" placeholder="请选择账户类型">
            <el-option
              v-for="dict in dict.type.fund_account_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="账户用途" prop="accountUsage">
          <el-select v-model="form.accountUsage" placeholder="请选择账户用途">
            <el-option
              v-for="dict in dict.type.fund_account_usage"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="账户所属机构" prop="accountOwner">
          <el-input v-model="form.accountOwner" placeholder="请输入账户所属机构" />
        </el-form-item>
        <el-form-item label="账号机构号码" prop="accountOwnerCode">
          <el-input v-model="form.accountOwnerCode" placeholder="请输入账号机构号码" />
        </el-form-item>
        <el-form-item label="账号机构地址" prop="accountOwnerAddr">
          <el-input v-model="form.accountOwnerAddr" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择状态">
            <el-option
              v-for="dict in dict.type.sys_data_status"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
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
import { listAccount, getAccount, delAccount, addAccount, updateAccount } from "@/api/fund/account";

export default {
  name: "Account",
  dicts: ['sys_data_status', 'fund_account_type', 'fund_account_usage'],
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
      // 充值帐号表格数据
      accountList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        accountName: null,
        accountNo: null,
        accountCurrency: null,
        accountType: null,
        accountUsage: "withdraw",
        accountOwnerCode: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        accountType: [
          { required: true, message: "账户类型不能为空", trigger: "change" }
        ],
        accountUsage: [
          { required: true, message: "账户用途不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询充值帐号列表 */
    getList() {
      this.loading = true;
      this.queryParams.accountUsage = "withdraw";
      listAccount(this.queryParams).then(response => {
        this.accountList = response.rows;
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
        userId: null,
        userName: null,
        accountName: null,
        accountNo: null,
        accountCurrency: null,
        accountType: null,
        accountUsage: null,
        accountOwner: null,
        accountOwnerCode: null,
        accountOwnerAddr: null,
        remark: null,
        status: null,
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
      this.title = "添加充值帐号";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getAccount(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改充值帐号";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateAccount(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAccount(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除充值帐号编号为"' + ids + '"的数据项？').then(function() {
        return delAccount(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('fund/account/export', {
        ...this.queryParams
      }, `account_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
