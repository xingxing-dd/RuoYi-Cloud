<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="订单号" prop="orderId">
        <el-input
          v-model="queryParams.orderId"
          placeholder="请输入订单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户名" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入用户名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品编号" prop="productCode">
        <el-input
          v-model="queryParams.productCode"
          placeholder="请输入产品编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="交易方向" prop="tradeDirect">
        <el-input
          v-model="queryParams.tradeDirect"
          placeholder="请输入交易方向"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in dict.type.trade_order_status"
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
          v-hasPermi="['fund:trade:add']"
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
          v-hasPermi="['fund:trade:edit']"
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
          v-hasPermi="['fund:trade:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['fund:trade:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="tradeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="订单编号" align="center" prop="id" />-->
      <el-table-column label="订单号" align="center" prop="orderId" />
      <el-table-column label="用户id" align="center" prop="userId" />
      <el-table-column label="用户名" align="center" prop="userName" />
      <el-table-column label="产品编号" align="center" prop="productCode" />
      <el-table-column label="倍率" align="center" prop="multiplier" />
      <el-table-column label="保证金" align="center" prop="margin" />
      <el-table-column label="止损点" align="center" prop="stopLoss" />
      <el-table-column label="止盈点" align="center" prop="stopProfit" />
      <el-table-column label="张数" align="center" prop="sheetNum" />
      <el-table-column label="交易方向" align="center" prop="tradeDirect">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.trade_direct" :value="scope.row.tradeDirect"/>
        </template>
      </el-table-column>
      <el-table-column label="买入价格" align="center" prop="tradePrice" />
      <el-table-column label="交易金额" align="center" prop="tradeAmount" />
      <el-table-column label="卖出价格" align="center" prop="deliveryPrice" />
      <el-table-column label="交割金额" align="center" prop="deliveryAmount" />
      <el-table-column label="手续费" align="center" prop="feeAmount" />
      <el-table-column label="收益" align="center" prop="income" />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.trade_order_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="交割时间" align="center" prop="deliveryTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.deliveryTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新人" align="center" prop="updateBy" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateBy, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['fund:trade:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['fund:trade:remove']"
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

    <!-- 添加或修改交易订单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="订单号" prop="orderId">
          <el-input v-model="form.orderId" placeholder="请输入订单号" />
        </el-form-item>
        <el-form-item label="用户id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户id" />
        </el-form-item>
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="产品编号" prop="productCode">
          <el-input v-model="form.productCode" placeholder="请输入产品编号" />
        </el-form-item>
        <el-form-item label="倍率" prop="multiplier">
          <el-input v-model="form.multiplier" placeholder="请输入倍率" />
        </el-form-item>
        <el-form-item label="保证金" prop="margin">
          <el-input v-model="form.margin" placeholder="请输入保证金" />
        </el-form-item>
        <el-form-item label="止损点" prop="stopLoss">
          <el-input v-model="form.stopLoss" placeholder="请输入止损点" />
        </el-form-item>
        <el-form-item label="止盈点" prop="stopProfit">
          <el-input v-model="form.stopProfit" placeholder="请输入止盈点" />
        </el-form-item>
        <el-form-item label="张数" prop="sheetNum">
          <el-input v-model="form.sheetNum" placeholder="请输入张数" />
        </el-form-item>
        <el-form-item label="交易方向" prop="tradeDirect">
          <el-input v-model="form.tradeDirect" placeholder="请输入交易方向" />
        </el-form-item>
        <el-form-item label="买入价格" prop="tradePrice">
          <el-input v-model="form.tradePrice" placeholder="请输入买入价格" />
        </el-form-item>
        <el-form-item label="交易金额" prop="tradeAmount">
          <el-input v-model="form.tradeAmount" placeholder="请输入交易金额" />
        </el-form-item>
        <el-form-item label="卖出价格" prop="deliveryPrice">
          <el-input v-model="form.deliveryPrice" placeholder="请输入卖出价格" />
        </el-form-item>
        <el-form-item label="交割金额" prop="deliveryAmount">
          <el-input v-model="form.deliveryAmount" placeholder="请输入交割金额" />
        </el-form-item>
        <el-form-item label="手续费" prop="feeAmount">
          <el-input v-model="form.feeAmount" placeholder="请输入手续费" />
        </el-form-item>
        <el-form-item label="收益" prop="income">
          <el-input v-model="form.income" placeholder="请输入收益" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择状态">
            <el-option
              v-for="dict in dict.type.trade_order_status"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="交割时间" prop="deliveryTime">
          <el-date-picker clearable
                          v-model="form.deliveryTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择交割时间">
          </el-date-picker>
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
import { listTrade, getTrade, delTrade, addTrade, updateTrade } from "@/api/fund/trade";

export default {
  name: "Trade",
  dicts: ['trade_order_status'],
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
      // 交易订单表格数据
      tradeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderId: null,
        userName: null,
        productCode: null,
        tradeDirect: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        orderId: [
          { required: true, message: "订单号不能为空", trigger: "blur" }
        ],
        userId: [
          { required: true, message: "用户id不能为空", trigger: "blur" }
        ],
        productCode: [
          { required: true, message: "产品编号不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询交易订单列表 */
    getList() {
      this.loading = true;
      listTrade(this.queryParams).then(response => {
        this.tradeList = response.rows;
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
        orderId: null,
        userId: null,
        userName: null,
        productCode: null,
        multiplier: null,
        margin: null,
        stopLoss: null,
        stopProfit: null,
        sheetNum: null,
        tradeDirect: null,
        tradePrice: null,
        tradeAmount: null,
        deliveryPrice: null,
        deliveryAmount: null,
        feeAmount: null,
        income: null,
        status: null,
        createTime: null,
        createBy: null,
        deliveryTime: null,
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
      this.title = "添加交易订单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getTrade(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改交易订单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateTrade(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTrade(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除交易订单编号为"' + ids + '"的数据项？').then(function() {
        return delTrade(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('fund/trade/export', {
        ...this.queryParams
      }, `trade_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
