<template>
	<view>
		<view class="form-header">
			压浆材料配合比设计试验检测记录表（二）
		</view>
		<uni-section title="砼试表CS42" type="line">
			<view class="example">
				<uni-forms ref="valiForm" :rules="rules" :modelValue="record" label-position="top">
					<uni-row>
						<uni-col :span="14" style="padding-right: 3px;">
							<uni-forms-item label="检测单位名称" required name="jcdw" label-width="100px">
								<uni-easyinput v-model="record.jcdw" placeholder="请输入检测单位名称" />
							</uni-forms-item>
						</uni-col>

						<uni-col :span="10">
							<uni-forms-item label="记录编号" required name="jlbh">
								<uni-easyinput v-model="record.jlbh" placeholder="请输入记录编号" />
							</uni-forms-item>
						</uni-col>

						<uni-col :span="12" style="padding-right: 3px;">
							<uni-forms-item label="工程名称" required name="gcmc">
								<uni-easyinput v-model="record.gcmc" placeholder="请输入工程名称" />
							</uni-forms-item>
						</uni-col>

						<uni-col :span="12">
							<uni-forms-item label="合同段" required name="htd">
								<uni-easyinput v-model="record.htd" placeholder="请输入合同段" />
							</uni-forms-item>
						</uni-col>

						<uni-col :span="12" style="padding-right: 3px;">
							<uni-forms-item label="施工单位" required name="sgdw">
								<uni-easyinput v-model="record.sgdw" placeholder="请输入施工单位" />
							</uni-forms-item>
						</uni-col>

						<uni-col :span="12">
							<uni-forms-item label="监理单位" required name="jldw">
								<uni-easyinput v-model="record.jldw" placeholder="请输入监理单位" />
							</uni-forms-item>
						</uni-col>

						<uni-col :span="24">
							<uni-forms-item label="工程部位/用途" required name="gcbw" label-width="200px">
								<uni-easyinput v-model="record.gcbw" placeholder="请输入工程部位" />
							</uni-forms-item>
						</uni-col>

						<uni-col :span="24">
							<uni-forms-item label="样品信息" required name="ypxx">
								<uni-easyinput v-model="record.ypxx" placeholder="请输入样品信息" />
							</uni-forms-item>
						</uni-col>

						<uni-col :span="12" style="padding-right: 3px;">
							<uni-forms-item label="试验检测日期" required name="jcrq" label-width="200px">
								<uni-datetime-picker type="datetime" return-type="timestamp" v-model="record.jcrq" />
							</uni-forms-item>
						</uni-col>

						<uni-col :span="12">
							<uni-forms-item label="试验条件" required name="cstj">
								<uni-easyinput v-model="record.cstj" placeholder="请输入温度：  ℃ ，相对湿度：  %" />
							</uni-forms-item>
						</uni-col>
						<uni-col :span="12">
							<uni-forms-item label="检测依据" required name="jcyj" style="padding-right: 3px;">
								<uni-easyinput v-model="record.jcyj" placeholder="请输入检测依据" />
							</uni-forms-item>
						</uni-col>
						<uni-col :span="12">
							<uni-forms-item label="判定依据" required name="pdyj">
								<uni-easyinput v-model="record.pdyj" placeholder="请输入判定依据" />
							</uni-forms-item>
						</uni-col>
						<uni-col :span="24">
							<uni-forms-item label="主要仪器设备名称及其编号" required name="zyyqsbmc" label-width="200px">
								<uni-easyinput v-model="record.zyyqsbmc" placeholder="请输入主要仪器设备名称及其编号" />
							</uni-forms-item>
						</uni-col>

						<uni-section title="3、试拌调整压浆材料拌合物性能" type="line">
							<uni-table border stripe>
								<!-- 复杂表头 -->
								<uni-tr>
									<uni-th rowspan="2">水胶比</uni-th>
									<uni-th colspan="3">流动度(s)</uni-th>
									<uni-th colspan="2">泌水率(%)</uni-th>
									<uni-th colspan="2">自由膨胀率(%)</uni-th>
									<uni-th colspan="2">压力泌水率(%)</uni-th>
									<uni-th rowspan="2">充盈度</uni-th>
									<uni-th colspan="2">凝结时间(min)</uni-th>
									<uni-th rowspan="2">备注</uni-th>
									<button class="add-button" type="primary" @click="addRow">+</button>
								</uni-tr>
								<uni-tr>
									<uni-th>初始</uni-th>
									<uni-th>30min</uni-th>
									<uni-th>60min</uni-th>
									<uni-th>3h钢丝间泌水率</uni-th>
									<uni-th>24h自由泌水率</uni-th>
									<uni-th>3h</uni-th>
									<uni-th>24h</uni-th>
									<uni-th>0.22MPa</uni-th>
									<uni-th>0.36MPa</uni-th>
									<uni-th>初凝</uni-th>
									<uni-th>终凝</uni-th>
								</uni-tr>
								<!-- 表格数据 -->
								<uni-tr v-for="(item, index) in table3" :key="index">
									<uni-td><uni-easyinput v-model="item.sjb3" /></uni-td>
									<uni-td><uni-easyinput v-model="item.lddcs" /></uni-td>
									<uni-td><uni-easyinput v-model="item.ldd30" /></uni-td>
									<uni-td><uni-easyinput v-model="item.ldd60" /></uni-td>
									<uni-td><uni-easyinput v-model="item.msl3" /></uni-td>
									<uni-td><uni-easyinput v-model="item.msl24" /></uni-td>
									<uni-td><uni-easyinput v-model="item.zypzl3" /></uni-td>
									<uni-td><uni-easyinput v-model="item.zypzl24" /></uni-td>
									<uni-td><uni-easyinput v-model="item.ylmsl22" /></uni-td>
									<uni-td><uni-easyinput v-model="item.ylmsl36" /></uni-td>
									<uni-td><uni-easyinput v-model="item.cyd" /></uni-td>
									<uni-td><uni-easyinput v-model="item.njsjcn" /></uni-td>
									<uni-td><uni-easyinput v-model="item.njsjzn" /></uni-td>
									<uni-td><uni-easyinput v-model="item.bz" /></uni-td>
									<uni-td>
										<button class="delete-button" type="default"
											@click="deleteRow(index)">删除</button>
									</uni-td>
								</uni-tr>
							</uni-table>
						</uni-section>

						<uni-section title="4、检验强度：" type="line">
							<uni-table border stripe>
								<!-- 表头 -->
								<uni-tr>
									<uni-th rowspan="2">水胶比</uni-th>
									<uni-th rowspan="2">试件数量</uni-th>
									<uni-th rowspan="2">养护方式</uni-th>
									<uni-th rowspan="2">成型日期</uni-th>
									<uni-th colspan="2">压件日期</uni-th>
									<uni-th colspan="2">抗折强度(MPa)</uni-th>
									<uni-th colspan="2">抗压强度(MPa)</uni-th>
									<button class="add-button" type="primary" @click="addRow4">+</button>
								</uni-tr>
								<uni-tr>
									<uni-th>7d</uni-th>
									<uni-th>28d</uni-th>
									<uni-th>7d</uni-th>
									<uni-th>28d</uni-th>
									<uni-th>7d</uni-th>
									<uni-th>28d</uni-th>
								</uni-tr>
								<!-- 表格数据 -->
								<uni-tr v-for="(item, index) in table4" :key="index">
									<uni-td><uni-easyinput v-model="item.sjb4" /></uni-td>
									<uni-td><uni-easyinput v-model="item.sjsl" /></uni-td>
									<uni-td><uni-easyinput v-model="item.yhfs" /></uni-td>
									<uni-td>
										<uni-datetime-picker type="datetime" return-type="timestamp"
											v-model="item.cxrq" />
									</uni-td>
									<uni-td>
										<uni-datetime-picker type="datetime" return-type="timestamp"
											v-model="item.yjrq7" />
									</uni-td>
									<uni-td>
										<uni-datetime-picker type="datetime" return-type="timestamp"
											v-model="item.yjrq28" />
									</uni-td>
									<uni-td><uni-easyinput v-model="item.kzqd7" /></uni-td>
									<uni-td><uni-easyinput v-model="item.kzqd28" /></uni-td>
									<uni-td><uni-easyinput v-model="item.kyqd7" /></uni-td>
									<uni-td><uni-easyinput v-model="item.kyqd28" /></uni-td>
									<uni-td>
										<button class="delete-button" type="default"
											@click="deleteRow4(index)">删除</button>
									</uni-td>
								</uni-tr>
							</uni-table>
						</uni-section>

						<uni-section title="5、确定配合比：每m3压浆材料各种材料用量(kg)" type="line">
							<uni-table border stripe>
								<uni-tr>
									<uni-th rowspan="2">水胶比</uni-th>
									<uni-th rowspan="2">水泥</uni-th>
									<uni-th rowspan="2">水</uni-th>
									<uni-th rowspan="2">外加剂</uni-th>
									<uni-th>外掺料</uni-th>
								</uni-tr>
								<!-- 表格数据 -->
								<uni-tr>
									<uni-td><uni-easyinput v-model="record.wclname" /></uni-td>
								</uni-tr>
								<uni-tr>
									<uni-td><uni-easyinput v-model="record.sjb5" /></uni-td>
									<uni-td><uni-easyinput v-model="record.sn" /></uni-td>
									<uni-td><uni-easyinput v-model="record.s" /></uni-td>
									<uni-td><uni-easyinput v-model="record.wjj" /></uni-td>
									<!-- <uni-td><uni-easyinput v-model="record.wclname" /></uni-td> -->
									<uni-td><uni-easyinput v-model="record.wcl" /></uni-td>
								</uni-tr>
							</uni-table>
						</uni-section>

						<uni-section title="6、压浆材料配合比校正：" type="line">
							<uni-table border stripe>
								<uni-tr>
									<uni-th>压浆材料理论表观密度(kg/m3)</uni-th>
									<uni-th>压浆材料实测表观密度(kg/m3)</uni-th>
									<uni-th>压浆材料配合比校正系数</uni-th>
								</uni-tr>
								<!-- 表格数据 -->
								<uni-tr>
									<uni-td><uni-easyinput v-model="record.llmd" /></uni-td>
									<uni-td><uni-easyinput v-model="record.scmd" /></uni-td>
									<uni-td><uni-easyinput v-model="record.jzxs" /></uni-td>
								</uni-tr>
								<uni-tr>
									<uni-td colspan="3">
										<uni-easyinput v-model="record.fjsm" width="100%" placeholder="附加声明:" />
									</uni-td>
								</uni-tr>
							</uni-table>
						</uni-section>

						<uni-col :span="12" style="padding-right: 3px;">
							<button type="default" form-type="reset">重置</button>
						</uni-col>
						<uni-col :span="12">
							<button type="primary" @click="submit('valiForm')">提交</button>
						</uni-col>

					</uni-row>
				</uni-forms>
			</view>
		</uni-section>
	</view>
</template>

<script>
	import {
		addCS42Record,
		getCS42Records
	} from '@/api/system/record';
	export default {
		data() {
			return {
				// 主表数据
				record: {
					id: '',
					jcdw: '', // 检测单位名称
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
					njsjcn: '', // 凝结时间 // c
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
					fjsm: '' // 附加声明
				},
				table3: [{
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
				}],
				table4: [{
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
				}],
				// 校验规则
				rules: {
					jcdw: {
						rules: [{
							required: true,
							errorMessage: '检测单位名称不能为空',
						}]
					},
					jlbh: {
						rules: [{
							required: true,
							errorMessage: '记录编号不能为空',
						}]
					},
					gcmc: {
						rules: [{
							required: true,
							errorMessage: '工程名称不能为空',
						}]
					},
					htd: {
						rules: [{
							required: true,
							errorMessage: '合同段不能为空',
						}]
					},
					sgdw: {
						rules: [{
							required: true,
							errorMessage: '施工单位不能为空',
						}]
					},
					jldw: {
						rules: [{
							required: true,
							errorMessage: '监理单位不能为空',
						}]
					},
					gcbw: {
						rules: [{
							required: true,
							errorMessage: '工程部位/用途不能为空',
						}]
					},
					ypxx: {
						rules: [{
							required: true,
							errorMessage: '样品信息不能为空',
						}]
					},
					jcrq: {
						rules: [{
							required: true,
							errorMessage: '试验检测日期不能为空',
						}]
					},
					cstj: {
						rules: [{
							required: true,
							errorMessage: '试验条件不能为空',
						}]
					},
					jcyj: {
						rules: [{
							required: true,
							errorMessage: '检测依据不能为空',
						}]
					},
					pdyj: {
						rules: [{
							required: true,
							errorMessage: '判定依据不能为空',
						}]
					},
					zyyqsbmc: {
						rules: [{
							required: true,
							errorMessage: '主要仪器设备名称及其编号不能为空',
						}]
					}
				}
			}
		},
		computed: {},
		onLoad() {},
		onReady() {
			// 设置自定义表单校验规则，必须在节点渲染完毕后执行
			// this.$refs.customForm.setRules(this.customRules)
		},
		methods: {
			//添加表3新行
			addRow() {
				this.table3.push({
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
					njsjcn: '', // 凝结时间 // c
					njsjzn: '', // 终凝
					bz: '' // 备注
				});
			},
			//删除表3行
			deleteRow(index) {
				this.table3.splice(index, 1);
			},
			//添加表4行
			addRow4() {
				this.table4.push({
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
				});
			},
			//删除表4行
			deleteRow4(index) {
				this.table4.splice(index, 1);
			},
			onClickItem(e) {
				console.log(e);
				this.current = e.currentIndex
			},
			submit() {
				this.$refs.valiForm.validate().then((res) => {
					const formDataArray = [];

					// 确保 table3 和 table4 是数组
					const table3 = Array.isArray(this.table3) ? this.table3 : [];
					const table4 = Array.isArray(this.table4) ? this.table4 : [];

					// 处理 table3 和 table4
					const maxLength = Math.max(table3.length, table4.length);

					for (let i = 0; i < maxLength; i++) {
						const formData = {
							...this.record
						}; // 先复制 record 中的数据

						// 合并 table3 中的数据
						if (i < table3.length) {
							const table3Item = table3[i];
							Object.assign(formData, {
								sjb3: table3Item.sjb3,
								lddcs: table3Item.lddcs,
								ldd30: table3Item.ldd30,
								ldd60: table3Item.ldd60,
								msl3: table3Item.msl3,
								msl24: table3Item.msl24,
								zypzl3: table3Item.zypzl3,
								zypzl24: table3Item.zypzl24,
								ylmsl22: table3Item.ylmsl22,
								ylmsl36: table3Item.ylmsl36,
								cyd: table3Item.cyd,
								njsjcn: table3Item.njsjcn,
								njsjzn: table3Item.njsjzn,
								bz: table3Item.bz
							});
						}

						// 合并 table4 中的数据
						if (i < table4.length) {
							const table4Item = table4[i];
							Object.assign(formData, {
								sjb4: table4Item.sjb4,
								sjsl: table4Item.sjsl,
								yhfs: table4Item.yhfs,
								cxrq: table4Item.cxrq,
								yjrq7: table4Item.yjrq7,
								yjrq28: table4Item.yjrq28,
								kzqd7: table4Item.kzqd7,
								kzqd28: table4Item.kzqd28,
								kyqd7: table4Item.kyqd7,
								kyqd28: table4Item.kyqd28
							});
						}

						formDataArray.push(formData);
					}

					// 打印所有要提交的数据，用于调试
					console.log('所有要提交的数据:', JSON.stringify(formDataArray, null, 2));

					// 批量提交数据
					formDataArray.forEach(formData => {
						addCS42Record(formData).then(data => {
							uni.showToast({
								title: `添加成功`
							});
						}).catch(error => {
							console.error('提交失败:', error);
							uni.showToast({
								title: `提交失败`,
								icon: 'none'
							});
						});
					});
				}).catch((errors) => {
					errors.forEach((error) => {
						uni.showToast({
							title: error.errorMessage,
							icon: 'none'
						});
					});
				});
			}
		}
	};
</script>


<style>
	.example {
		padding: 15px;
		background-color: #fff;
	}

	.segmented-control {
		margin-bottom: 15px;
	}

	.button-group {
		margin-top: 15px;
		display: flex;
		justify-content: space-around;
	}

	.form-item {
		display: flex;
		align-items: center;
	}

	.button {
		display: flex;
		align-items: center;
		height: 35px;
		margin-left: 10px;
	}

	.unit {
		display: inline-block;
		/* 使单位显示为行内块元素 */
		text-align: center;
		/* 文本居中 */
		padding: 6px 0;
		/* 添加一些上下内边距 */
	}

	/* 新增的表单标题行样式 */
	.form-header {
		font-size: 20px;
		/* 放大字体 */
		text-align: center;
		/* 居中对齐 */
		font-weight: bold;
		/* 加粗 */
		margin-bottom: 15px;
		/* 调整底部间距 */
		background-color: #f0f0f0;
		/* 背景色 */
		padding: 10px;
		/* 内边距 */
	}

	.add-button {
		width: 32px;
		height: 32px;
		display: flex;
		align-items: center;
		justify-content: center;
		border-radius: 4px;
		background-color: #1aad19;
		color: white;
		font-size: 18px;
		line-height: 1;
		box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
		transition: all 0.3s ease;
	}

	.add-button:hover {
		background-color: #159112;
		box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
	}

	.delete-button {
		width: 60px;
		height: 32px;
		display: flex;
		align-items: center;
		justify-content: center;
		border-radius: 4px;
		background-color: #ff4d4f;
		color: white;
		font-size: 14px;
		line-height: 1;
		box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
		transition: all 0.3s ease;
	}

	.delete-button:hover {
		background-color: #e23b3b;
		box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
	}
</style>