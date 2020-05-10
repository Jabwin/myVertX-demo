package xyz.jabwin.myVertX.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import xyz.jabwin.myVertX.constant.OrderType;
import xyz.jabwin.myVertX.pojo.service.*;
import xyz.jabwin.myVertX.tools.Arith;
import xyz.jabwin.myVertX.tools.MapNavUtil;
import xyz.jabwin.myVertX.tools.StringUtil;
import xyz.jabwin.myVertX.tools.TimeUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*************************************************************
 * 类：
 * @author Jabwin
 *************************************************************
 */
public class ValuationService
{
//    public List<OrderPriceVO> getServerCarTypeAndPrice(ServerCarTypeAndPriceDto serverCarTypeAndPrice)
//    {
//        Integer cityCode = serverCarTypeAndPrice.getCityCode();
//        //预约用车时间
//        LocalDateTime useCarDate = TimeUtil.convertTime(serverCarTypeAndPrice.getUseCarDate());
//        //预约用车时间-时
//        int nowHour = useCarDate.getHour();
//        //预约用车时间-分
//        int nowMinute = useCarDate.getMinute();
//        //出发地经纬度
//        String startCoordinate = serverCarTypeAndPrice.getDepartLongitude() + "," + serverCarTypeAndPrice.getDepartLatitude();
//        //目的地经纬度
//        String endCoordinate = serverCarTypeAndPrice.getDestinationLongitude() + "," + serverCarTypeAndPrice.getDestinationLatitude();
//        //通过出发地经纬度和目的地经纬度获取路径规划
//        MapNavResults mapNavResults = MapNavUtil.getMapNavResults(startCoordinate, endCoordinate);
//        //查询该城市下可服务的车型
//        CityTypeConf cityTypeConf = this.getCityTypeConf(cityCode, serverCarTypeAndPrice.getOrderType());
//        //获取计价规则
//        ValuationConf valuationConf = new ValuationConf();
////                (ValuationConf) redisUtil.get(CacheNames.CITY_VALUATIONCONF + cityCode + "::" + 1 + "_" + serverCarTypeAndPrice.getOrderType() + "_1");
//        //基础计价
//        List<BasicsValuationVO> basicsValuationVOs =
//                JSONArray.parseArray(valuationConf.getBasicsValuationJson(), BasicsValuationVO.class);
//
//        //围栏调价
//        List<PenValuationVo> penValuationVos =
//                JSONArray.parseArray(valuationConf.getPenConfigJson(), PenValuationVo.class);
//
//        //日期调价
//        List<TimeAdjustmentPriceVo> timeAdjustmentPriceVos =
//                JSONArray.parseArray(valuationConf.getTimeAdjustmentPriceJson(), TimeAdjustmentPriceVo.class);
//
//        //预估里程（公里）
//        Double estimateMileage = Arith.div(Double.parseDouble(mapNavResults.getDistance()), 1000);
//        //预估时间（分钟）
//        Double estimateTime = Arith.div(Double.parseDouble(mapNavResults.getDuration()), 60);
//
//        if (serverCarTypeAndPrice.getOrderType().equals(OrderType.半日租) ||
//                serverCarTypeAndPrice.getOrderType().equals(OrderType.日租)) {
//            //行驶里程
//            estimateMileage = 1d;
//            estimateTime = 1d;
//        }
//
//        Double timeAdjustmentPrice = 0d;
//        Integer adjustPriceType = null;
//        //日期调价
//        for (TimeAdjustmentPriceVo timeAdjustmentPriceVo : timeAdjustmentPriceVos) {
//            if (!StringUtil.isNoneBlank(timeAdjustmentPriceVo.getStartTime()) ||
//                    !StringUtil.isNoneBlank(timeAdjustmentPriceVo.getStartTime())) {
//                break;
//            }
//
//            //分段时间里程开始时间
//            Date startTime = DateUtil.parse(timeAdjustmentPriceVo.getStartTime(), "yyyy-MM-dd");
//            //分段时间里程结束时间
//            Date endTime = DateUtil.parse(timeAdjustmentPriceVo.getEndTime(), "yyyy-MM-dd");
//            //用车时间
//            Date useCar = DateUtil.parse(useCarDate, "yyyy-MM-dd");
//            if (startTime.getTime() <= useCar.getTime() && endTime.getTime() > useCar.getTime()) {
//                timeAdjustmentPrice = timeAdjustmentPriceVo.getTimeAdjustmentPrice();
//                adjustPriceType = timeAdjustmentPriceVo.getAdjustPriceType();
//                break;
//            }
//        }
//
//        //jts封装线段
//        List<Geometry> geolist = mapNavResults.getGeolist();
//        //特殊区域费（围栏价格）
//        Double estimatePenPrice = this.penValuationPrice(penValuationVos, geolist);
//
//        //加价费用状态
//        Integer addPriceStatus = valuationConf.getAddPriceStatus();
//
//        //第三方加价
//        ThirdPartyConfVO thirdPartyConfVO =
//                JSONObject.parseObject(valuationConf.getThirdPartyConfJson(), ThirdPartyConfVO.class);
//        //第三方上调比例
//        Double priceUpRegulation = thirdPartyConfVO.getPriceUpRegulation();
//        //第三方平台对订单的扣点
//        Double thirdPartyDeduction = thirdPartyConfVO.getThirdPartyDeduction();
//
//        List<OrderPriceVO> orderPriceVOS = new ArrayList<>();
//        sctfor:
//        for (String sct : cityTypeConf.getServeCarType().split(",")) {
//
//            OrderPriceVO orderPriceVO = new OrderPriceVO();
//            OrderRealTimePriceVO orderRealTimePriceVO =
//                    this.calculatePrice(basicsValuationVOs, Integer.parseInt(sct), estimateMileage, estimateTime, nowHour,
//                            nowMinute, adjustPriceType, timeAdjustmentPrice, addPriceStatus, priceUpRegulation, estimatePenPrice, thirdPartyDeduction, 0d);
//
//            //城市编码
//            orderPriceVO.setCityCode(cityCode.toString());
//            orderPriceVO.setCityName(serverCarTypeAndPrice.getCityName());
//            orderPriceVO.setProvinceCode(serverCarTypeAndPrice.getProvinceCode().toString());
//            orderPriceVO.setProvinceName(serverCarTypeAndPrice.getProvinceName());
//            //下单车辆服务类型
//            orderPriceVO.setServeCarType(Integer.parseInt(sct));
//            //服务类型Code
//            orderPriceVO.setOrderTypeClassesCode(cityTypeConf.getOrderTypeClassesCode());
//            //服务类别
//            orderPriceVO.setOrderTypeClasses(cityTypeConf.getOrderTypeClasses());
//            //服务类型
//            orderPriceVO.setOrderType(serverCarTypeAndPrice.getOrderType());
//            //预估里程
//            orderPriceVO.setEstimateMileage(estimateMileage);
//            //预估时间
//            orderPriceVO.setEstimateTime(Math.toIntExact(Math.round(estimateTime)));
//            //起步公里数
//            orderPriceVO.setEstimateInitiateKilometre(orderRealTimePriceVO.getRealTimeInitiateKilometre());
//            //起步费
//            orderPriceVO.setEstimateInitiatePrice(orderRealTimePriceVO.getRealTimeInitiatePrice());
//            //除去起步里程的里程
//            orderPriceVO.setEstimateMileageKilometre(orderRealTimePriceVO.getRealTimeMileageKilometre());
//            //分段里程费
//            orderPriceVO.setEstimateMileagePrice(orderRealTimePriceVO.getRealTimeMileagePrice());
//            //除去起步时长的时长
//            orderPriceVO.setEstimateDuration(orderRealTimePriceVO.getRealTimeDuration());
//            //分段时长费
//            orderPriceVO.setEstimateDurationPrice(orderRealTimePriceVO.getRealTimeDurationPrice());
//            //特殊区域费
//            orderPriceVO.setEstimatePenPrice(new BigDecimal(estimatePenPrice).setScale(2, RoundingMode.HALF_UP));
//            //预估金额
//            orderPriceVO.setEstimatePrice(orderRealTimePriceVO.getRealTimePrice());
//            /* 出发地信息 */
//            orderPriceVO.setDepartLocation(serverCarTypeAndPrice.getDepartLocation());
//            orderPriceVO.setDepartLongitude(serverCarTypeAndPrice.getDepartLongitude());
//            orderPriceVO.setDepartLatitude(serverCarTypeAndPrice.getDepartLatitude());
//            /* 目的地信息 */
//            orderPriceVO.setDestinationLocation(serverCarTypeAndPrice.getDestinationLocation());
//            orderPriceVO.setDestinationLongitude(serverCarTypeAndPrice.getDestinationLongitude());
//            orderPriceVO.setDestinationLatitude(serverCarTypeAndPrice.getDestinationLatitude());
//            //三方平台对订单扣点
//            orderPriceVO.setThirdPartyDeduction(thirdPartyConfVO.getThirdPartyDeduction());
//            //价格上调比例
//            orderPriceVO.setPriceUpRegulation(priceUpRegulation);
//            //价格上调金额
//            orderPriceVO.setPriceUp(orderRealTimePriceVO.getPriceUp());
//            //todo 优惠券抵扣计算 待B做完之后再算
//
//            orderPriceVOS.add(orderPriceVO);
//        }
//        return orderPriceVOS;
//    }

    private CityTypeConf getCityTypeConf(Integer cityCode, Integer orderType) {
        //通过城市和业务类型查询城市下可服务的服务类型
        List<CityTypeConf> cityTypeConfs = new ArrayList<>();
//                (List<CityTypeConf>) redisUtil.get(CacheNames.CITY_ORDER_TYPE + cityCode + "_" + 1);
//        if (null == cityTypeConfs || cityTypeConfs.size() <= 0) {
//            cityTypeConfs = valuationMapper.getOrderType(cityCode, 1);
//            if (null != cityTypeConfs && cityTypeConfs.size() > 0) {
//                redisUtil.set(CacheNames.CITY_ORDER_TYPE + cityCode + "_" + 1,
//                        valuationMapper.getOrderType(cityCode, 1));
//            }
//        }

        CityTypeConf cityTypeConf = new CityTypeConf();
        ctc:
        for (CityTypeConf ctcs : cityTypeConfs)
        {
            for (String ot : ctcs.getOrderType().split(","))
            {
                if (orderType.equals(Integer.parseInt(ot)))
                {
                    cityTypeConf = ctcs;
                    break ctc;
                }
            }
        }
        return cityTypeConf;
    }
    private Double penValuationPrice(List<PenValuationVo> penValuationVos, List<Geometry> geolist) {
        int threanum = geolist.size();
        Double price = 0d;
        //围栏调价
        for (PenValuationVo valuationVo : penValuationVos) {
            if (threanum > 0) {
                //判断 是否经过围栏
                price = Arith.add(this.isPassPen(geolist, valuationVo), price);
            }
        }
        return price;
    }
    private Double isPassPen(List<Geometry> geolist, PenValuationVo valuationVo) {
        Double penPrice = 0d;
        //通过围栏ID获取围栏信息
        PenConfig penConfig = new PenConfig();
//                valuationMapper.getPenConfigById(valuationVo.getPenConfigId());
        //图像
        Geometry pic;
        //线段
        List<JTSrulst> jtsruls = getJTSRUL(penConfig);
        jt:
        for (JTSrulst jtsrul : jtsruls) {
            pic = jtsrul.getCoorlist();
            for (Geometry line : geolist) {
                boolean isIter = pic.intersects(line);
                if (isIter) {    //经过此围栏
                    penPrice = valuationVo.getPenPrice();
                    break jt;
                }
            }
        }
        return penPrice;
    }
    public List<JTSrulst> getJTSRUL(PenConfig penConfig) {
        JSONArray jsonArray = JSONArray.parseArray(penConfig.getPenLongitudeLatitudeJson());
        List<JTSrulst> jtsrulstlist = new ArrayList<>();
        JTSrulst jstrul;
        if (jsonArray.size() != 0) {
            Geometry g1;
            Coordinate[] coordinates1;
            double l2x1, l2y1;

            for (int i = 0; i < jsonArray.size(); i++) {
                jstrul = new JTSrulst();
                // 判断点是否在多边形内
                // 获取围栏点
                List<PenLongitudeLatitudeVO> jsonpointsarr = jsonArray.getJSONArray(i).toJavaList(PenLongitudeLatitudeVO.class);
                coordinates1 = new Coordinate[jsonpointsarr.size() + 1];
                for (int c = 0; c < jsonpointsarr.size(); c++) {
                    l2x1 = jsonpointsarr.get(c).getLon();
                    l2y1 = jsonpointsarr.get(c).getLat();
                    coordinates1[c] = new Coordinate(l2x1, l2y1);
                }
                l2x1 = jsonpointsarr.get(0).getLon();
                l2y1 = jsonpointsarr.get(0).getLat();
                coordinates1[jsonpointsarr.size()] = new Coordinate(l2x1, l2y1);

                g1 = new GeometryFactory().createPolygon(coordinates1);
                jstrul.setCoorlist(g1);
                jtsrulstlist.add(jstrul);
            }
        }
        return jtsrulstlist;
    }
//    private OrderRealTimePriceVO calculatePrice(List<BasicsValuationVO> basicsValuationVOs, Integer serveCarType,
//                                                Double estimateMileage, Double estimateTime, Integer nowHour,
//                                                Integer nowMinute, Integer adjustPriceType, Double timeAdjustmentPrice,
//                                                Integer addPriceStatus, Double priceUpRegulation, Double estimatePenPrice,
//                                                Double thirdPartyDeduction, Double waitTime) {
//
//        OrderRealTimePriceVO orderRealTimePriceVO = new OrderRealTimePriceVO();
//        BasicsValuationVO basicsValuation = null;
//        //获取对应的计价规则
//        for (BasicsValuationVO basicsValuationVO : basicsValuationVOs) {
//            if (null == basicsValuationVO.getServerCarType()) {
//                return orderRealTimePriceVO;
//            }
//            if (basicsValuationVO.getServerCarType().equals(serveCarType)) {
//                basicsValuation = basicsValuationVO;
//                break;
//            }
//        }
//        if (null == basicsValuation) {
//            return orderRealTimePriceVO;
//        }
//        //起步公里数
//        Double initiateKilometre = basicsValuation.getInitiateKilometre();
//        //除去起步里程的里程
//        Double estimateMileageKilometre = Arith.sub(estimateMileage, initiateKilometre);
//        //起步时长
//        Double initiateDuratione = basicsValuation.getInitiateDuratione();
//        //除去起步时长的时长
//        Double estimateDuration = Arith.sub(estimateTime, initiateDuratione);
//        //计算起步价
//        Double estimateInitiatePrice = this.calculateInitiatePrice(basicsValuation, nowHour, nowMinute);
//        //计算里程费
//        Double estimateMileagePrice = this.calculateEstimateMileagePrice(estimateMileageKilometre, basicsValuation, nowHour, nowMinute);
//        //计算时长费
//        Double estimateDurationPrice = this.calculateEstimateDurationPrice(estimateDuration, basicsValuation, nowHour, nowMinute);
//        //计算等待时长
//        Double waitPrice = this.calculateWaitPrice(waitTime, basicsValuation);
//
//        if (adjustPriceType != null) {
//            if (adjustPriceType.equals(1)) {    //倍率
//                estimateInitiatePrice = Arith.mul(estimateInitiatePrice, timeAdjustmentPrice);
//                estimateMileagePrice = Arith.mul(estimateMileagePrice, timeAdjustmentPrice);
//                estimateDurationPrice = Arith.mul(estimateDurationPrice, timeAdjustmentPrice);
//            } else {
//                Double price = Arith.div(timeAdjustmentPrice, 3, 2);
//                estimateInitiatePrice = Arith.add(estimateInitiatePrice, price);
//                estimateMileagePrice = Arith.add(estimateMileagePrice, price);
//                estimateDurationPrice = Arith.add(estimateDurationPrice, price);
//            }
//        }
//
//        //预估费用 = 起步费+里程费+时长费+特殊区域费+等待费用
//        Double priceUp = 0d;
//        Double estimatePrice = Arith.add(Arith.add(Arith.add(Arith.add(estimateInitiatePrice, estimateMileagePrice), estimateDurationPrice), estimatePenPrice), waitPrice);
//        if (addPriceStatus.equals(1)) {
//            //第三方扣点
//            priceUp = Arith.mul(estimatePrice, Arith.div(priceUpRegulation, 100));
//            //estimatePrice = Arith.add(estimatePrice,priceUp);
//        }
//
//        orderRealTimePriceVO.setRealTimePenPrice(new BigDecimal(estimatePenPrice).setScale(2, BigDecimal.ROUND_DOWN));
//        orderRealTimePriceVO.setRealTimePrice(new BigDecimal(estimatePrice).setScale(2, BigDecimal.ROUND_DOWN));
//        orderRealTimePriceVO.setRealTimeInitiateKilometre(initiateKilometre);
//        orderRealTimePriceVO.setRealTimeInitiatePrice(new BigDecimal(estimateInitiatePrice).setScale(2, BigDecimal.ROUND_DOWN));
//        orderRealTimePriceVO.setRealTimeMileageKilometre(estimateMileageKilometre);
//        orderRealTimePriceVO.setRealTimeMileagePrice(new BigDecimal(estimateMileagePrice).setScale(2, BigDecimal.ROUND_DOWN));
//        orderRealTimePriceVO.setRealTimeDuration(estimateDuration);
//        orderRealTimePriceVO.setRealTimeDurationPrice(new BigDecimal(estimateDurationPrice).setScale(2, BigDecimal.ROUND_DOWN));
//        orderRealTimePriceVO.setWaitPrice(new BigDecimal(waitPrice).setScale(2, BigDecimal.ROUND_DOWN));
//        if (addPriceStatus.equals(1)) {
//            orderRealTimePriceVO.setThirdPartyDeduction(thirdPartyDeduction);
//            orderRealTimePriceVO.setPriceUpRegulation(priceUpRegulation);
//            orderRealTimePriceVO.setPriceUp(new BigDecimal(priceUp).setScale(2, BigDecimal.ROUND_DOWN));
//        }
//        return orderRealTimePriceVO;
//
//    }

}
