package com.mushiny.wms.masterdata.obbasics.exception;

public enum OutBoundException {

    /**
     * OUT BOUND ENTITY EXCEPTION
     **/
    EX_MD_OB_BOX_TYPE_NAME_UNIQUE,// {0}已存在！

    EX_MD_OB_CARRIER_UNIQUE,// {0}已存在！

    EX_MD_OB_LABEL_CONTROLLER_NAME_UNIQUE,// {0}已存在！

    EX_MD_OB_DIGITAL_LABEL_NAME_UNIQUE,// {0}已存在！

    EX_CUSTOMER_ORDER_NO_UNIQUE,

    EX_CUSTOMER_SHIPMENT_NO_UNIQUE,

    EX_MD_OB_ORDER_STRATEGY_NAME_UNIQUE,

    EX_MD_OB_PROCESS_PATH_NAME_UNIQUE,

    EX_MD_OB_PROCESS_PATH_TYPE_NAME_UNIQUE,

    EX_REBATCH_SLOT_NAME_UNIQUE,

    EX_REBATCH_STATION_NAME_UNIQUE,

    EX_REBATCH_STATION_TYPE_NAME_UNIQUE,

    EX_MD_OB_REBIN_CELL_NAME_UNIQUE,

    EX_MD_OB_REBIN_CELL_TYPE_NAME_UNIQUE,

    EX_MD_OB_REBIN_STATION_NAME_UNIQUE,

    EX_MD_OB_REBIN_STATION_TYPE_NAME_UNIQUE,

    EX_REBIN_WALL_NAME_UNIQUE,

    EX_MD_OB_REBIN_WALL_TYPE_NAME_UNIQUE,

    EX_MD_OB_PICK_STATION_NAME_UNIQUE,

    EX_MD_OB_PICKING_CATE_GORY_NAME_UNIQUE,

    EX_MD_OB_PICKING_CATE_GORY_POSITION_POSITIONNO_UNIQUE,

    EX_MD_OB_PACKING_STATION_TYPE_NAME_UNIQUE,

    EX_MD_OB_PACKING_STATION_NAME_UNIQUE,

    EX_MD_OB_PICKING_AREA_NAME_UNIQUE,

    EX_MD_OB_PICKPACK_FIELD_TYPE_NAME_UNIQUE,// 名称已存在

    EX_MD_OB_PICKPACK_CELL_NAME_UNIQUE,// 名称已存在

    EX_MD_OB_PICKPACK_CELL_TYPE_NAME_UNIQUE,// 名称已存在

    EX_MD_OB_PICKPACK_WALL_NAME_UNIQUE,// 名称已存在

    EX_MD_OB_PICKPACK_WALL_TYPE_NAME_UNIQUE,// 名称已存在

    EX_MD_OB_REBIN_WALL_NAME_UNIQUE,// 名称已存在

    EX_MD_OB_REBATCH_SLOT_NAME_UNIQUE, // 名称:{0}已存在!

    EX_MD_OB_REBATCH_STATION_TYPE_NAME_UNIQUE,

    EX_MD_OB_SORTING_STATION_TYPE_NAME_UNIQUE,

    EX_MD_OB_WORKSTATION_NOT_FOUND,

    EX_MD_OB_DATA_NOT_FOUND,

    EX_STORAGE_STRATEGY_NAME_UNIQUE,

    EX_PICKING_CART_NOT_FOUND,

    EX_MD_OB_GOODS_OUT_DOOR_NAME_UNIQUE,

    /**
     * OTHER EXCEPTION
     **/
    EX_SCANNING_OBJECT_NOT_FOUND,// 扫描对象{0},不存在！

    EX_PICKING_ORDER_NOT_FOUND,// 小车{0}中不存在任何拣货单

    EX_CLIENT_NOT_FOUND_BOX,// 没有找到{0}的包装容器！
    EX_SKU_NOT_CUBI_SCAN,// 商品:{0},没有测量！

    EX_SHIPMENT_NOT_IS_REBINED,// 拣货单{0}没有完成ReBin

    EX_CONTAINER_NOT_FOUND_SHIPMENT,// 小车{0}中不存在任何拣货单

    EX_REBIN_WALL_NOT_FOUND_SHIPMENT,// ReBin墙{0}中不存在任何拣货单

    EX_SORTING_SORT_CODE_HAS_USED,// 目的地{0},已经被使用！

    EX_SORTING_CONTAINER_HAS_USED,// 笼车{0},已经被使用！

    EX_SORTING_CONTAINER_NOT_FOUND,// 笼车{0},未存在绑定关系中！

    EX_SORTING_SHIPMENT_NOT_PACKED,// 订单{0},未完成包装！

    EX_SORTING_SHIPMENT_HAS_SORTED,//订单: {0} 已经转移至笼车{1}中，不得重复移动

    EX_SORTING_SHIPMENT_SORT_CODE_NOT_EQUALS,// 笼车目的地{0}和订单目的地{1}不符合！

    EX_PICKING_AREA_NOT_FOUND,// 拣货区域未设置

}
