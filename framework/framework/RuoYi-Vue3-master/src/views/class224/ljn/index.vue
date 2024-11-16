<template>
	<div class="app-container">
		<el-row :gutter="24">
			<!--用户数据-->
			<el-col :span="24" :xs="24">
				<el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch">
					<el-form-item label="检测单位名称" prop="jcdw" label-width="100px">
						<el-input v-model="queryParams.jcdw" placeholder="请输入检测单位名称" clearable style="width: 240px"
							@keyup.enter="handleQuery" />
					</el-form-item>
					<el-form-item label="记录编号" prop="jlbh" label-width="69px">
						<el-input v-model="queryParams.jlbh" placeholder="请输入记录编号" clearable style="width: 240px"
							@keyup.enter="handleQuery" />
					</el-form-item>
					<el-form-item>
						<el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
						<el-button icon="Refresh" @click="resetQuery">重置</el-button>
					</el-form-item>
				</el-form>

				<el-row :gutter="10" class="mb8">
					<el-col :span="1.5">
						<el-button type="primary" plain icon="Plus" @click="handleAdd"
							v-hasPermi="['system:contract:add']">新增</el-button>
					</el-col>
					<el-col :span="1.5">
						<el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate"
							v-hasPermi="['system:contract:edit']">修改</el-button>
					</el-col>
					<el-col :span="1.5">
						<el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete"
							v-hasPermi="['system:contract:remove']">删除</el-button>
					</el-col>
					<el-col :span="1.5">
						<el-button type="info" plain icon="Upload" @click="handleImport"
							v-hasPermi="['system:contract:import']">导入</el-button>
					</el-col>
					<el-col :span="1.5">
						<el-button type="warning" plain icon="Download" @click="handleExport"
							v-hasPermi="['system:contract:export']">导出</el-button>
					</el-col>
					<right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
				</el-row>

				<el-table v-loading="loading" :data="contractList" @selection-change="handleSelectionChange">
					<el-table-column type="selection" width="50" align="center" />
					<el-table-column label="检测单位名称" align="center" key="jcdw" prop="jcdw" />
					<el-table-column label="记录编号" align="center" key="jlbh" prop="jlbh" />
					<el-table-column label="工程名称" align="center" key="gcmc" prop="gcmc" />
					<el-table-column label="合同段" align="center" key="htd" prop="htd" />
					<el-table-column label="施工单位" align="center" key="sgdw" prop="sgdw" />
					<el-table-column label="监理单位" align="center" key="jldw" prop="jldw" />
					<el-table-column label="工程部位/用途" align="center" key="gcbw" prop="gcbw" />
					<el-table-column label="样品信息" align="center" key="ypxx" prop="ypxx" />
					<el-table-column label="检测日期" align="center" key="jcrq" prop="jcrq" />


					<el-table-column label="操作" align="center" width="150" class-name="small-padding fixed-width">
						<template #default="scope">
							<el-tooltip content="修改" placement="top" v-if="scope.row.id !== null">
								<el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
									v-hasPermi="['system:contract:edit']"></el-button>
							</el-tooltip>
							<el-tooltip content="删除" placement="top" v-if="scope.row.id !== null">
								<el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
									v-hasPermi="['system:contract:remove']"></el-button>
							</el-tooltip>
							<el-tooltip content="查看详情" placement="top" v-if="scope.row.id !== null">
								<el-button link type="primary" icon="View" @click="handleDetail(scope.row)"
									v-hasPermi="['system:contract:view']"></el-button>
							</el-tooltip>

						</template>
					</el-table-column>
				</el-table>
				<pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum"
					v-model:limit="queryParams.pageSize" @pagination="getList" />
			</el-col>
		</el-row>

		<!-- 添加或修改压浆材料配合比配置对话框 -->
		<el-dialog :title="title" v-model="open" width="600px" append-to-body>
			<el-form :model="form" :rules="rules" ref="contractRef" label-width="80px" label-position="top">
				<h1 class="form-header" style="text-align: center;">压浆材料配合比设计试验检测记录表（二）</h1>
				<el-row>
					<el-col :span="14" style="padding-right: 3px;">
						<el-form-item label="检测单位名称" prop="jcdw" label-width="100px">
							<el-input v-model="form.jcdw" placeholder="请输入检测单位名称" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="10">
						<el-form-item label="记录编号" prop="jlbh">
							<el-input v-model="form.jlbh" placeholder="请输入记录编号" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12" style="padding-right: 3px;">
						<el-form-item label="工程名称" prop="gcmc">
							<el-input v-model="form.gcmc" placeholder="请输入工程名称" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="合同段" prop="htd">
							<el-input v-model="form.htd" placeholder="请输入合同段" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12" style="padding-right: 3px;">
						<el-form-item label="施工单位" prop="sgdw">
							<el-input v-model="form.sgdw" placeholder="请输入施工单位" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="监理单位" prop="jldw">
							<el-input v-model="form.jldw" placeholder="请输入监理单位" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="24">
						<el-form-item label="工程部位/用途" prop="gcbw" label-width="100px">
							<el-input v-model="form.gcbw" placeholder="请输入工程部位/用途" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="24">
						<el-form-item label="样品信息" prop="ypxx">
							<el-input v-model="form.ypxx" placeholder="请输入样品信息" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12" style="padding-right: 3px;">
						<el-form-item label="试验检测日期" prop="jcrq" label-width="100px">
							<el-date-picker type="datetime" v-model="form.jcrq" placeholder="请选择试验检测日期"
								maxlength="30"></el-date-picker>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="试验条件" prop="cstj">
							<el-input v-model="form.cstj" placeholder="请输入温度：  ℃ ，相对湿度：  %" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12" style="padding-right: 3px;">
						<el-form-item label="检测依据" prop="jcyj">
							<el-input v-model="form.jcyj" placeholder="请输入检测依据" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="判定依据" prop="pdyj">
							<el-input v-model="form.pdyj" placeholder="请输入判定依据" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="24">
						<el-form-item label="主要仪器设备名称及其编号" prop="zyyqsbmc" label-width="100px">
							<el-input v-model="form.zyyqsbmc" placeholder="请输入主要仪器设备名称及其编号" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<h2 class="form-header" style="text-align: left;">3、试拌调整压浆材料拌合物性能</h2>
				<el-row>
					<el-col :span="24">
						<el-form-item label="水胶比" prop="sjb3">
							<el-input v-model="form.sjb3" placeholder="水胶比" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="24">
						<el-form-item label="流动度(s)">
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="8">
						<el-form-item label="初始" prop="lddcs" style="padding-right: 3px;">
							<el-input v-model="form.lddcs" placeholder="初始" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="8">
						<el-form-item label="30min" prop="sjb3" style="padding-right: 3px;">
							<el-input v-model="form.ldd30" placeholder="30min" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="8">
						<el-form-item label="60min" prop="sjb3">
							<el-input v-model="form.ldd60" placeholder="60min" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="24">
						<el-form-item label="泌水率(%)">
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12">
						<el-form-item label="3h钢丝间泌水率" prop="msl3" style="padding-right: 3px;">
							<el-input v-model="form.msl3" placeholder="3h钢丝间泌水率" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="24h自由泌水率" prop="msl24">
							<el-input v-model="form.msl24" placeholder="24h自由泌水率" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="24">
						<el-form-item label="自由膨胀率(%)">
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12">
						<el-form-item label="3h" prop="zypzl3" style="padding-right: 3px;">
							<el-input v-model="form.zypzl3" placeholder="3h" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="24h" prop="zypzl24">
							<el-input v-model="form.zypzl24" placeholder="24h" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="24">
						<el-form-item label="压力泌水率(%)">
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12">
						<el-form-item label="0.22MPa" prop="ylmsl22" style="padding-right: 3px;">
							<el-input v-model="form.ylmsl22" placeholder="0.22MPa" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="0.36MPa" prop="ylmsl36">
							<el-input v-model="form.ylmsl36" placeholder="0.36MPa" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="24">
						<el-form-item label="充盈度" prop="cyd">
							<el-input v-model="form.cyd" placeholder="充盈度" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="24">
						<el-form-item label="凝结时间(min)">
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12">
						<el-form-item label="初凝" prop="njsjcn" style="padding-right: 3px;">
							<el-input v-model="form.njsjcn" placeholder="初凝" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="终凝" prop="njsjzn">
							<el-input v-model="form.njsjzn" placeholder="终凝" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="24">
						<el-form-item label="备注" prop="bz">
							<el-input v-model="form.bz" placeholder="备注" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
				</el-row>

				<h2 class="form-header" style="text-align: left;">4、检验强度：</h2>
				<el-row>
					<el-col :span="24">
						<el-form-item label="水胶比" prop="sjb4">
							<el-input v-model="form.sjb4" placeholder="水胶比" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="24">
						<el-form-item label="试件数量" prop="sjsl">
							<el-input v-model="form.sjsl" placeholder="试件数量" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="24">
						<el-form-item label="养护方式" prop="yhfs">
							<el-input v-model="form.yhfs" placeholder="养护方式" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="24">
						<el-form-item label="成型日期" prop="cxrq" label-width="100px">
							<el-date-picker type="datetime" v-model="form.cxrq" placeholder="成型日期"
								maxlength="30"></el-date-picker>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="24">
						<el-form-item label="压件日期">
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12" style="padding-right: 3px;">
						<el-form-item label="7d" prop="yjrq7" label-width="100px">
							<el-date-picker type="datetime" v-model="form.yjrq7" placeholder="7d"
								maxlength="30"></el-date-picker>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="28d" prop="yjrq28" label-width="100px">
							<el-date-picker type="datetime" v-model="form.yjrq28" placeholder="28d"
								maxlength="30"></el-date-picker>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="24">
						<el-form-item label="抗折强度(MPa)">
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12">
						<el-form-item label="7d" prop="kzqd7" style="padding-right: 3px;">
							<el-input v-model="form.kzqd7" placeholder="7d" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="28d" prop="kzqd28">
							<el-input v-model="form.kzqd28" placeholder="28d" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="24">
						<el-form-item label="抗压强度(MPa)">
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12">
						<el-form-item label="7d" prop="kyqd7" style="padding-right: 3px;">
							<el-input v-model="form.kyqd7" placeholder="7d" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="28d" prop="kyqd28">
							<el-input v-model="form.kyqd28" placeholder="28d" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
				</el-row>

				<h2 class="form-header" style="text-align: left;">5、确定配合比：每m3压浆材料各种材料用量(kg)</h2>
				<el-row>
					<el-col :span="24">
						<el-form-item label="水胶比" prop="sjb5">
							<el-input v-model="form.sjb5" placeholder="水胶比" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="24">
						<el-form-item label="水泥" prop="sn">
							<el-input v-model="form.sn" placeholder="水泥" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="24">
						<el-form-item label="水" prop="s">
							<el-input v-model="form.s" placeholder="水" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="24">
						<el-form-item label="外加剂" prop="wjj">
							<el-input v-model="form.wjj" placeholder="外加剂" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="24">
						<el-form-item label="外掺料" prop="wcl">
							<el-input v-model="form.wclname" placeholder="外掺料" maxlength="30"></el-input>
							<el-input v-model="form.wcl" placeholder="外掺料" maxlength="30"></el-input>
						</el-form-item>
					</el-col>
				</el-row>

			<h2 class="form-header" style="text-align: left;">6、压浆材料配合比校正：</h2>
			<el-row>
				<el-col :span="24">
					<el-form-item label="压浆材料理论表观密度(kg/m3)" prop="llmd" label-width="100px">
						<el-input v-model="form.llmd" placeholder="压浆材料理论表观密度" maxlength="30"></el-input>
					</el-form-item>
				</el-col>
			</el-row>
			<el-row>
				<el-col :span="24">
					<el-form-item label="压浆材料实测表观密度(kg/m3)" prop="scmd" label-width="100px">
						<el-input v-model="form.scmd" placeholder="压浆材料实测表观密度" maxlength="30"></el-input>
					</el-form-item>
				</el-col>
			</el-row>
			<el-row>
				<el-col :span="24">
					<el-form-item label="压浆材料配合比校正系数" prop="jzxs" label-width="100px">
						<el-input v-model="form.jzxs" placeholder="压浆材料配合比校正系数" maxlength="30"></el-input>
					</el-form-item>
				</el-col>
			</el-row>

			<el-row>
				<el-col :span="24">
					<el-form-item label="附加声明:" prop="fjsm" label-width="100px">
						<el-input v-model="form.fjsm" placeholder="附加声明:" maxlength="30"></el-input>
					</el-form-item>
				</el-col>
			</el-row>


			</el-form>
			<template #footer>
				<div class="dialog-footer">
					<el-button type="primary" @click="submitForm">确 定</el-button>
					<el-button @click="cancel">取 消</el-button>
				</div>
			</template>
		</el-dialog>


		<!-- 压浆材料配合比数据导入对话框 -->
		<el-dialog :title="upload.title" v-model="upload.open" width="400px" append-to-body>
			<el-upload ref="uploadRef" :limit="1" accept=".xlsx, .xls" :headers="upload.headers"
				:action="upload.url + '?updateSupport=' + upload.updateSupport" :disabled="upload.isUploading"
				:on-progress="handleFileUploadProgress" :on-success="handleFileSuccess" :auto-upload="false" drag>
				<el-icon class="el-icon--upload"><upload-filled /></el-icon>
				<div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
				<template #tip>
					<div class="el-upload__tip text-center">
						<div class="el-upload__tip">
							<el-checkbox v-model="upload.updateSupport" />是否更新已经存在的压浆材料配合比数据数据
						</div>
						<span>仅允许导入xls、xlsx格式文件。</span>
						<el-link type="primary" :underline="false" style="font-size:12px;vertical-align: baseline;"
							@click="importTemplate">下载模板</el-link>
					</div>
				</template>
			</el-upload>
			<template #footer>
				<div class="dialog-footer">
					<el-button type="primary" @click="submitFileForm">确 定</el-button>
					<el-button @click="upload.open = false">取 消</el-button>
				</div>
			</template>
		</el-dialog>

		<el-dialog title="查看压浆材料配合比设计试验检测记录表（二）详情" v-model="openExcel" width="900px" append-to-body>
			<el-form :model="form" :rules="rules" ref="contractRef" label-width="80px">
				<!-- 表格内容 -->
				<table id="CS42Table" style="width: 100%; border-collapse: collapse; font-size: 14px;">
					<tr style="text-align: center; height: 23.8px;">
						<td colspan="11" style="border: transparent; font-weight: bold; text-align:left">砼试表CS42</td>
						<td colspan="33" style="border: transparent;"></td>
						<td colspan="7" style="border: transparent; text-align:right">第 页，共 页</td>
					</tr>
					<tr style="border: 1px solid black; text-align: center; font-weight: bold;">
						<td colspan="51" style="height: 23.8px; font-size: 16px; border: 1px solid black;">
							压浆材料配合比设计试验检测记录表（二）
						</td>
					</tr>
					<tr style="border: 1px solid black; text-align: center;height: 40px;">
						<td colspan="8" style="border: 1px solid black;">检测单位名称</td>
						<td colspan="28" style="border: 1px solid black;">{{ form.jcdw }}</td>
						<td colspan="5" style="border: 1px solid black;">记录编号</td>
						<td colspan="10" style="border: 1px solid black;">{{ form.jlbh }}</td>
					</tr>
					<tr style="border: 1px solid black; text-align: center; height: 30px;">
						<td colspan="10" style="border: 1px solid black;">工程名称</td>
						<td colspan="22" style="border: 1px solid black;">{{ form.gcmc }}</td>
						<td colspan="7" style="border: 1px solid black;">合同段</td>
						<td colspan="12" style="border: 1px solid black;">{{ form.htd }}</td>
					</tr>
					<tr style="border: 1px solid black; text-align: center; height: 30px;">
						<td colspan="10" style="border: 1px solid black;">施工单位</td>
						<td colspan="22" style="border: 1px solid black;">{{ form.gcmc }}</td>
						<td colspan="7" style="border: 1px solid black;">监理单位</td>
						<td colspan="12" style="border: 1px solid black;">{{ form.jldw }}</td>
					</tr>
					<tr style="border: 1px solid black; text-align: center;height: 30px;">
						<td colspan="10" style="border: 1px solid black;">工程部位/用途</td>
						<td colspan="41" style="border: 1px solid black;">{{ form.gcbw }}</td>
					</tr>
					<tr style="border: 1px solid black; text-align: center;height: 30px;">
						<td colspan="10" style="border: 1px solid black;">样品信息</td>
						<td colspan="41" style="border: 1px solid black;">{{ form.ypxx }}</td>
					</tr>
					<tr style="border: 1px solid black; text-align: center;height: 30px;">
						<td colspan="10" style="border: 1px solid black;">试验检测日期</td>
						<td colspan="22" style="border: 1px solid black;">{{ form.jcrq }}</td>
						<td colspan="7" style="border: 1px solid black;">试验条件</td>
						<td colspan="12" style="border: 1px solid black;">{{ form.cstj }}</td>
					</tr>
					<tr style="border: 1px solid black; text-align: center;height: 30px;">
						<td colspan="10" style="border: 1px solid black;">检测依据</td>
						<td colspan="22" style="border: 1px solid black;">{{ form.jcyj }}</td>
						<td colspan="7" style="border: 1px solid black;">判定依据</td>
						<td colspan="12" style="border: 1px solid black;">{{ form.pdyj }}</td>
					</tr>
					<tr style="border: 1px solid black; text-align: center;height: 33.4px;">
						<td colspan="10" style="border: 1px solid black;">主要仪器设备名称及编号</td>
						<td colspan="41" style="border: 1px solid black;">{{ form.zyyqsbmc }}</td>
					</tr>
					<tr style="text-align:center; height: 30px;">
						<td colspan="51" style="border: 1px solid black; text-align:left;">3、试拌调整压浆材料拌合物性能</td>
					</tr>
					<tr style="border: 1px solid black; text-align: center;height: 35px;">
						<td colspan="4" rowspan="2" style="border: 1px solid black;">水胶比</td>
						<td colspan="9" style="border: 1px solid black;">流动度(s)</td>
						<td colspan="12" style="border: 1px solid black;">泌水率(%)</td>
						<td colspan="6" style="border: 1px solid black;">自由膨胀率(%)</td>
						<td colspan="8" style="border: 1px solid black;">压力泌水率(%)</td>
						<td colspan="3" rowspan="2" style="border: 1px solid black;">充盈度</td>
						<td colspan="6" style="border: 1px solid black;">凝结时间(min)</td>
						<td colspan="3" rowspan="2" style="border: 1px solid black;">备注</td>
					</tr>
					<tr style="border: 1px solid black; text-align: center;height: 35px;">
						<td colspan="3" style="border: 1px solid black;">初始</td>
						<td colspan="3" style="border: 1px solid black;">30min</td>
						<td colspan="3" style="border: 1px solid black;">60min</td>
						<td colspan="6" style="border: 1px solid black;">3h钢丝间泌水率</td>
						<td colspan="6" style="border: 1px solid black;">24h自由泌水率</td>
						<td colspan="3" style="border: 1px solid black;">3h</td>
						<td colspan="3" style="border: 1px solid black;">24h</td>
						<td colspan="4" style="border: 1px solid black;">0.22MPa</td>
						<td colspan="4" style="border: 1px solid black;">0.36MPa</td>
						<td colspan="3" style="border: 1px solid black;">初凝</td>
						<td colspan="3" style="border: 1px solid black;">终凝</td>
					</tr>
					<tbody id="sbtz"></tbody>

					<tr style="text-align:center; height: 30px;">
						<td colspan="51" style="border: 1px solid black; text-align:left;">4、检验强度：</td>
					</tr>
					<tr style="border: 1px solid black; text-align: center;height: 30px;">
						<td colspan="4" rowspan="2" style="border: 1px solid black;">水胶比</td>
						<td colspan="5" rowspan="2" style="border: 1px solid black;">试件数量</td>
						<td colspan="5" rowspan="2" style="border: 1px solid black;">养护方式</td>
						<td colspan="6" rowspan="2" style="border: 1px solid black;">成型日期</td>
						<td colspan="11" style="border: 1px solid black;">压件日期</td>
						<td colspan="10" style="border: 1px solid black;">抗折强度(MPa)</td>
						<td colspan="10" style="border: 1px solid black;">抗压强度(MPa)</td>
					</tr>
					<tr style="border: 1px solid black; text-align: center;height: 30px;">
						<td colspan="6" style="border: 1px solid black;">7d</td>
						<td colspan="5" style="border: 1px solid black;">28d</td>
						<td colspan="5" style="border: 1px solid black;">7d</td>
						<td colspan="5" style="border: 1px solid black;">28d</td>
						<td colspan="5" style="border: 1px solid black;">7d</td>
						<td colspan="5" style="border: 1px solid black;">28d</td>
					</tr>
					<tbody id="jyqd"></tbody>

					<tr style="text-align:center; height: 30px;">
						<td colspan="51" style="border: 1px solid black; text-align:left;">
							5、确定配合比：每m3压浆材料各种材料用量(kg)
						</td>
					</tr>
					<tr style="border: 1px solid black; text-align: center;height: 30px;">
						<td colspan="11" rowspan="2" style="border: 1px solid black;">水胶比</td>
						<td colspan="12" rowspan="2" style="border: 1px solid black;">水泥</td>
						<td colspan="10" rowspan="2" style="border: 1px solid black;">水</td>
						<td colspan="9" rowspan="2" style="border: 1px solid black;">外加剂</td>
						<td colspan="9" style="border: 1px solid black;">外掺料</td>
					</tr>
					<tr style="border: 1px solid black; text-align: center;height: 35px;">
						<td colspan="9">{{ form.wclname }}</td>
					</tr>
					<tr style="border: 1px solid black; text-align: center;height: 35px;">
						<td colspan="11">{{ form.sjb5 }}</td>
						<td colspan="12">{{ form.sn }}</td>
						<td colspan="10">{{ form.s }}</td>
						<td colspan="9">{{ form.wjj }}</td>
						<td colspan="9">{{ form.wcl }}</td>
					</tr>

					<tr style="text-align:center; height: 30px;">
						<td colspan="51" style="border: 1px solid black; text-align:left;">
							6、压浆材料配合比校正：
						</td>
					</tr>
					<tr style="border: 1px solid black; text-align: center;height: 30px;">
						<td colspan="19" style="border: 1px solid black;">压浆材料理论表观密度(kg/m3)</td>
						<td colspan="18" style="border: 1px solid black;">压浆材料实测表观密度(kg/m3)</td>
						<td colspan="14" style="border: 1px solid black;">压浆材料配合比校正系数</td>
					</tr>
					<tr style="border: 1px solid black; text-align: center;height: 30px;">
						<td colspan="19">{{ form.llmd }}</td>
						<td colspan="18">{{ form.scmd }}</td>
						<td colspan="14">{{ form.jzxs }}</td>
					</tr>
					<tr style="border: 1px solid black; text-align: center;height: 33.6px;">
						<td colspan="51" style="border: 1px solid black;text-align: left;">
							附加声明：{{ data.form.fjsm }}
						</td>
					</tr>

					<tr style="border: transparent; text-align: center;height: 35px;">
						<td colspan="5" style="border: transparent;">检测:</td>
						<td colspan="8" style="border: transparent;"></td>
						<td colspan="4" style="border: transparent;">记录:</td>
						<td colspan="9" style="border: transparent;"></td>
						<td colspan="4" style="border: transparent;">复核:</td>
						<td colspan="8" style="border: transparent;"></td>
						<td colspan="4" style="border: transparent;">日期:</td>
						<td colspan="9" style="border: transparent;"></td>
					</tr>
				</table>
			</el-form>

			<template #footer>
				<div class="dialog-footer">
					<el-button type="success" @click="downloadExcel">下载</el-button>
				</div>
			</template>
		</el-dialog>

	</div>
</template>

<script setup name="CS42Record">
	import {
		ref,
		reactive,
		toRefs
	} from 'vue';
	import {
		getCurrentInstance
	} from 'vue';
	import $ from 'jquery';
	import {
		getToken
	} from "@/utils/auth";
	import {
		getCS42Records,
		getCS42Record,
		addCS42Record,
		editCS42Record,
		deleteCS42Record
	} from "@/api/class224/record";

	// 定义初始数据结构
	const initialData = {
		table3: {
			sjb3: '', // 水胶比
			lddcs: '', // 流动度
			ldd30: '',
			ldd60: '',
			msl3: '', // 泌水率
			msl24: '',
			zypzl3: '', // 自由膨胀率
			zypzl24: '', // 自由膨胀率
			ylmsl22: '', // 压力泌水率
			ylmsl36: '', // 压力泌水率
			cyd: '', // 充盈度
			njsjcn: '', // 凝结时间 // 初凝
			njsjzn: '', // 终凝
			bz: '' // 备注
		},
		table4: {
			sjb4: '', // 水胶比
			sjsl: '', // 试件数量
			yhfs: '', // 养护方式
			cxrq: null, // 成型日期
			yjrq7: null, // 压件日期
			yjrq28: null,
			kzqd7: '', // 抗折强度
			kzqd28: '',
			kyqd7: '', // 抗压强度
			kyqd28: ''
		}
	};

	// 使用 reactive 创建响应式数组
	const experimentDataList = reactive([JSON.parse(JSON.stringify(initialData))]);

	// 校验后才能继续添加数据
	const FormRef = ref([]);
	// 选中的项
	const selectedItems = ref(experimentDataList.map(() => false));

	const router = useRouter();
	const {
		proxy
	} = getCurrentInstance();

	const contractList = ref([]);
	const open = ref(false);
	const openExcel = ref(false);
	const loading = ref(true);
	const showSearch = ref(true);
	const ids = ref([]);
	const single = ref(true);
	const multiple = ref(true);
	const total = ref(0);
	const title = ref("");
	const dateRange = ref([]);
	const deptName = ref("");
	const deptOptions = ref(undefined);
	const initPassword = ref(undefined);
	const postOptions = ref([]);
	const roleOptions = ref([]);
	/*** 压浆材料配合比数据导入参数 */
	const upload = reactive({
		// 是否显示弹出层（压浆材料配合比数据导入）
		open: false,
		// 弹出层标题（压浆材料配合比数据导入）
		title: "",
		// 是否禁用上传
		isUploading: false,
		// 是否更新已经存在的压浆材料配合比数据数据
		updateSupport: 0,
		// 设置上传的请求头部
		headers: {
			Authorization: "Bearer " + getToken()
		},
		// 上传的地址
		url: import.meta.env.VITE_APP_BASE_API + "/system/contract/importData"
	});

	const data = reactive({
		form: {},
		queryParams: {
			pageNum: 1,
			pageSize: 10,
			jlbh: null
		},
		rules: {
			jcdw: [{
				required: true,
				message: '检测单位名称不能为空',
				trigger: 'blur'
			}],
			jlbh: [{
				required: true,
				message: '记录编号不能为空',
				trigger: 'blur'
			}],
			gcmc: [{
				required: true,
				message: '工程名称不能为空',
				trigger: 'blur'
			}],
			htd: [{
				required: true,
				message: '合同段不能为空',
				trigger: 'blur'
			}],
			sgdw: [{
				required: true,
				message: '施工单位不能为空',
				trigger: 'blur'
			}],
			jldw: [{
				required: true,
				message: '监理单位不能为空',
				trigger: 'blur'
			}],
			gcbw: [{
				required: true,
				message: '工程部位/用途不能为空',
				trigger: 'blur'
			}],
			ypxx: [{
				required: true,
				message: '样品信息不能为空',
				trigger: 'blur'
			}],
			jcrq: [{
				required: true,
				message: '试验检测日期不能为空',
				trigger: 'blur'
			}],
			cstj: [{
				required: true,
				message: '试验条件不能为空',
				trigger: 'blur'
			}],
			jcyj: [{
				required: true,
				message: '检测依据不能为空',
				trigger: 'blur'
			}],
			pdyj: [{
				required: true,
				message: '判定依据不能为空',
				trigger: 'blur'
			}],
			zyyqsbmc: [{
				required: true,
				message: '主要仪器设备名称及其编号不能为空',
				trigger: 'blur'
			}],
		}
	});

	const {
		queryParams,
		form,
		rules
	} = toRefs(data);

	/** 通过条件过滤节点  */
	const filterNode = (value, data) => {
		if (!value) return true;
		return data.label.indexOf(value) !== -1;
	};

	/** 查询压浆材料配合比数据列表 */
	function getList() {
		loading.value = true;
		console.log(555);
		getCS42Records(queryParams.value).then(res => {
			console.log(123);
			console.log(res);
			loading.value = false;
			contractList.value = res.data.list;
			total.value = res.data.total;
		});
	};

	/** 节点单击事件 */
	function handleNodeClick(data) {
		queryParams.value.deptId = data.id;
		handleQuery();
	};

	/** 搜索按钮操作 */
	function handleQuery() {
		queryParams.value.pageNum = 1;
		getList();
	};

	/** 重置按钮操作 */
	function resetQuery() {
		//dateRange.value = [];
		proxy.resetForm("queryRef");
		//queryParams.value.deptId = undefined;
		//proxy.$refs.deptTreeRef.setCurrentKey(null);
		handleQuery();
	};

	/** 删除按钮操作 */
	function handleDelete(row) {
		const recordIds = row.id || ids.value;
		proxy.$modal.confirm('是否确认删除压浆材料配合比数据编号为"' + recordIds + '"的数据项？').then(function() {
			return deleteCS42Record(recordIds);
		}).then(() => {
			getList();
			proxy.$modal.msgSuccess("删除成功");
		}).catch(() => {});
	};

	/** 导出按钮操作 */
	function handleExport() {
		proxy.download("system/contract/export", {
			...queryParams.value,
		}, `contract_${new Date().getTime()}.xlsx`);
	};

	/** 更多操作 */
	function handleCommand(command, row) {
		switch (command) {
			case "handleResetPwd":
				handleResetPwd(row);
				break;
			case "handleAuthRole":
				handleAuthRole(row);
				break;
			default:
				break;
		}
	};

	/** 选择条数  */
	function handleSelectionChange(selection) {
		ids.value = selection.map(item => item.id);
		single.value = selection.length != 1;
		multiple.value = !selection.length;
	};

	/** 导入按钮操作 */
	function handleImport() {
		upload.title = "压浆材料配合比数据导入";
		upload.open = true;
	};

	/** 下载模板操作 */
	function importTemplate() {
		proxy.download("system/contract/importTemplate", {}, `contract_template_${new Date().getTime()}.xlsx`);
	};

	/**文件上传中处理 */
	const handleFileUploadProgress = (event, file, fileList) => {
		upload.isUploading = true;
	};

	/** 文件上传成功处理 */
	const handleFileSuccess = (response, file, fileList) => {
		upload.open = false;
		upload.isUploading = false;
		proxy.$refs["uploadRef"].handleRemove(file);
		proxy.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" +
			response.msg + "</div>", "导入结果", {
				dangerouslyUseHTMLString: true
			});
		getList();
	};

	/** 提交上传文件 */
	function submitFileForm() {
		proxy.$refs["uploadRef"].submit();
	};

	/** 重置操作表单 */
	function reset() {
		form.value = {
			id: '',
			jcdw: '', // 检测单位
			jlbh: '', // 记录编号
			gcmc: '', // 工程名称
			htd: '', // 合同段
			sgdw: '', // 施工单位
			jldw: '', // 监理单位
			gcbw: '', // 工程部位/用途
			ypxx: '', // 样品信息
			jcrq: null, // 试验检测日期
			cstj: '', // 试验条件
			jcyj: '', // 检测依据
			pdyj: '', // 判定依据
			zyyqsbmc: '', // 主要仪器设备名称及其编号
			sjb3: '', // 水胶比
			lddcs: '', // 流动度
			ldd30: '',
			ldd60: '',
			msl3: '', // 泌水率
			msl24: '',
			zypzl3: '', // 自由膨胀率
			zypzl24: '', // 自由膨胀率
			ylmsl22: '', // 压力泌水率
			ylmsl36: '', // 压力泌水率
			cyd: '', // 充盈度
			njsjcn: '', // 凝结时间 // 初凝
			njsjzn: '', // 终凝
			bz: '', // 备注
			sjb4: '', // 水胶比
			sjsl: '', // 试件数量
			yhfs: '', // 养护方式
			cxrq: null, // 成型日期
			yjrq7: null, // 压件日期
			yjrq28: null,
			kzqd7: '', // 抗折强度
			kzqd28: '',
			kyqd7: '', // 抗压强度
			kyqd28: '',
			wclname: '', // 外掺料名
			sjb5: '', // 水胶比
			sn: '', // 水泥
			s: '', // 水
			wjj: '', // 外加剂
			wcl: '', // 外掺料
			llmd: '', // 理论密度
			scmd: '', // 实测密度
			jzxs: '', // 校正系数
			fjsm: "附加声明:" + '' // 附加声明
		};
		proxy.resetForm("contractRef");
	};

	/** 取消按钮 */
	function cancel() {
		open.value = false;
		reset();
	};

	/** 新增按钮操作 */
	function handleAdd() {
		open.value = true;
		title.value = "添加压浆材料配合比数据";
	};

	/** 修改按钮操作 */
	function handleUpdate(row) {
		reset();
		const id = row.id || ids.value;
		getCS42Record(id).then(response => {
			form.value = response.data;
			open.value = true;
			title.value = "修改压浆材料配合比数据";
		});
	};

	/** 查看详情 */
	function handleDetail(row) {
		reset();
		const id = row.id || ids.value;
		getCS42Record(id).then(response => {
			form.value = response.data;
			openExcel.value = true;
			title.value = "查看压浆材料配合比数据";
			queryParams.value.jlbh = form.value.jlbh;
			console.log(100000000);
			console.log(form.value.jlbh);
			console.log(queryParams.value.jlbh);
			getsbtz();
			getjyqd();
		});
	};

	// 导出表格
	function downloadExcel() {
		$('#CS42Table').table2excel({
			name: '压浆材料配合比设计试验检测记录表（二）',
			filename: '压浆材料配合比设计试验检测记录表（二）',
			exclude_img: true, //导出图片
			exclude_links: true, // 导出连接
			exclude_inputs: true, //导出输入框
		})
	}
	/** 提交按钮 */
	function submitForm() {
		proxy.$refs["contractRef"].validate(valid => {
			if (valid) {
				if (form.value.id != undefined) {
					editCS42Record(form.value).then(response => {
						proxy.$modal.msgSuccess("修改成功");
						open.value = false;
						getList();
					});
				} else {
					addCS42Record(form.value).then(response => {
						proxy.$modal.msgSuccess("新增成功");
						open.value = false;
						getList();
					});
				}
				reset()
			}
		});
	};

	function getsbtz() {
		console.log(form.value);
		queryParams.value.jlbh = form.value.jlbh;
		console.log(11111111111);
		console.log(form.value.jlbh);
		console.log(queryParams.value);
		loading.value = true;
		queryParams.value.pageNum = null;
		queryParams.value.pageSize = null;
		getCS42Records(queryParams.value).then(res => {
			console.log(22222222222);
			console.log(res);
			console.log(res.data.list);
			var total = res.data.list.length;
			console.log(total);
			var html = '';

			// 定义一个函数来处理空值
			function formatValue(value) {
				return value !== null && value !== undefined && value !== '' ? value : '-';
			}

			// 检查一行是否所有字段都为空或0
			function isRowEmptyOrZero(row) {
				const fields = [
					'sjb3', 'lddcs', 'ldd30', 'ldd60', 'msl3', 'msl24',
					'zypzl3', 'zypzl24', 'ylmsl22', 'ylmsl36', 'cyd',
					'njsjcn', 'njsjzn', 'bz'
				];
				return fields.every(field => row[field] === null || row[field] === undefined || row[field] ===
					'' || row[field] === 0);
			}

			res.data.list.forEach(e => {
				if (!isRowEmptyOrZero(e)) {
					html += `<tr style="border: 1px solid black; text-align: center;height: 30px;">
	                            <td colspan="4" style="border: 1px solid black;">${formatValue(e.sjb3)}</td>
	                            <td colspan="3" style="border: 1px solid black;">${formatValue(e.lddcs)}</td>
	                            <td colspan="3" style="border: 1px solid black;">${formatValue(e.ldd30)}</td>
	                            <td colspan="3" style="border: 1px solid black;">${formatValue(e.ldd60)}</td>
	                            <td colspan="6" style="border: 1px solid black;">${formatValue(e.msl3)}</td>
	                            <td colspan="6" style="border: 1px solid black;">${formatValue(e.msl24)}</td>
	                            <td colspan="3" style="border: 1px solid black;">${formatValue(e.zypzl3)}</td>
	                            <td colspan="3" style="border: 1px solid black;">${formatValue(e.zypzl24)}</td>
	                            <td colspan="4" style="border: 1px solid black;">${formatValue(e.ylmsl22)}</td>
	                            <td colspan="4" style="border: 1px solid black;">${formatValue(e.ylmsl36)}</td>
	                            <td colspan="3" style="border: 1px solid black;">${formatValue(e.cyd)}</td>
	                            <td colspan="3" style="border: 1px solid black;">${formatValue(e.njsjcn)}</td>
	                            <td colspan="3" style="border: 1px solid black;">${formatValue(e.njsjzn)}</td>
	                            <td colspan="3" style="border: 1px solid black;">${formatValue(e.bz)}</td>
	                        </tr>`;
				}
			});

			$("#sbtz").html(html);
			loading.value = false;
		});
	}

	function getjyqd() {
		console.log(1111123123);
		console.log(form.value);
		queryParams.value.jlbh = form.value.jlbh;
		console.log(queryParams.value);
		loading.value = true;
		queryParams.value.pageNum = null;
		queryParams.value.pageSize = null;
		getCS42Records(queryParams.value).then(res => {
			console.log(res);
			console.log(res.data.list);
			var total = res.data.list.length;
			console.log(total);
			var html = '';

			// 定义一个函数来处理空值
			function formatValue(value) {
				return value !== null && value !== undefined && value !== '' ? value : '-';
			}

			// 检查一行是否所有字段都为空或0
			function isRowEmptyOrZero(row) {
				const fields = ['sjb4', 'sjsl', 'yhfs', 'cxrq', 'yjrq7', 'yjrq28', 'kzqd7', 'kzqd28', 'kyqd7',
					'kyqd28'
				];
				return fields.every(field => row[field] === null || row[field] === undefined || row[field] ===
					'' || row[field] === 0);
			}

			res.data.list.forEach(e => {
				if (!isRowEmptyOrZero(e)) {
					html += `<tr style="border: 1px solid black; text-align: center;height: 30px;">
	                            <td colspan="4" style="border: 1px solid black;">${formatValue(e.sjb4)}</td>
	                            <td colspan="5" style="border: 1px solid black;">${formatValue(e.sjsl)}</td>
	                            <td colspan="5" style="border: 1px solid black;">${formatValue(e.yhfs)}</td>
	                            <td colspan="6" style="border: 1px solid black;">${formatValue(e.cxrq)}</td>
	                            <td colspan="6" style="border: 1px solid black;">${formatValue(e.yjrq7)}</td>
	                            <td colspan="5" style="border: 1px solid black;">${formatValue(e.yjrq28)}</td>
	                            <td colspan="5" style="border: 1px solid black;">${formatValue(e.kzqd7)}</td>
	                            <td colspan="5" style="border: 1px solid black;">${formatValue(e.kzqd28)}</td>
	                            <td colspan="5" style="border: 1px solid black;">${formatValue(e.kyqd7)}</td>
	                            <td colspan="5" style="border: 1px solid black;">${formatValue(e.kyqd28)}</td>
	                        </tr>`;
				}
			});

			$("#jyqd").html(html);
			loading.value = false;
		});
	}

	getList();
</script>

<style>
	/* 添加表格样式以确保打印与导出效果一致 */
	#CS42Table td {
		padding: 8px;
		text-align: center;
		border: 1px solid black;
	}

	#CS42Table {
		font-size: 14px;
		width: 100%;
	}
</style>