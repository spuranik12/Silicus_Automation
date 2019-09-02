package automation.utilities;

import java.util.List;
import java.util.Map;


public interface IDataProvider {
	public Map<String, List<Object>> getSkuForClearanceSortPage() throws Exception;

	public String getSoldOutSkuForMaxAvailableInCart() throws Exception;

	public String getSellableRandomSKU() throws Exception;

	public String getSellableRandomSKUCertona() throws Exception;

	public String getRandomSKUForESL() throws Exception;

	public String getSellableRandomSKURelatedItem() throws Exception;

	public String getClearanceProductSku() throws Exception;

	public Map<String,List<Object>> getSkuWithMoreThanTenRelatedProducts() throws Exception;

	public String getAtrributeForLightKitApplicableForGlass( String componentValue) throws Exception;

	public Map<String, List<Object>> getCoordinatingProductDetails() throws Exception;

	public String getOrderIdToCheckCertonaOnOrderDetailPage() throws Exception;

	public List<String> getStoreLocationNumberInRegion(String region) throws Exception;

	public String getStoreLocationURL() throws Exception;

	public Map<String, String> getCategoryBrandTypeStyleLandingPagesUrl(String...columnNames) throws Exception;

	public String getShopByRoomDetailPage() throws Exception;

	public String getDesignItFinalizePageURL() throws Exception;

	public String getSkuForProductWithInventoryMoreThanZero() throws Exception;

	public String getBuildFullSystemDetails() throws Exception;

	public Map<String, List<Object>> getRecordsForBuildFullSystem() throws Exception;

	public List<Object> getTopNSellableRandomSKU( int count) throws Exception;

	public String getSKUOfUMRPProduct() throws Exception;

	public Integer getMaxDiscountForESIUser( String userEmail) throws Exception ;

	public String getSkuByShippingOption() throws Exception;

	public String getShippingDaysAndFreightCharge() throws Exception;

	public String getSkuForLeftOutItems() throws Exception;

	public String getSoldOutItemShortSku() throws Exception;

	public String GetRandomSellableSkuPriceGreaterThanTen() throws Exception;

	public String getFreeShippingAndReturnsItemShortSku() throws Exception;

	public String getFreeShippingAndReturnsItemPDPSku() throws Exception;

	public String getMoreOptionsCallOutShortSku() throws Exception;

	public String getFreeShippingSku() throws Exception;

	public String getDailySaleItemShortSku() throws Exception;

	public Map<String, List<Object>> get100PlusColourItemShortSku() throws Exception;

	public Map<String, List<Object>> getSearchPathForSortPage() throws Exception ;

	public Map<String, List<Object>> get16PlusColorItemSKU() throws Exception;

	public String getClearanceProductShortSku() throws Exception;

	public Map<String, List<Object>> getSaleItemDetails() throws Exception;

	public String getProductSkuOverDollar49() throws Exception;

	public String getNonLPProductWithOutCompareCallOutSku() throws Exception;

	public List<List<String>> getSkuWithQuantityAvailable() throws Exception;

	public Map<String, List<Object>> getFinishFamilySKU() throws Exception;

	public String getColorPlusSKU() throws Exception;

	public Map<String, List<Object>> getSkuForTradePriceForProfessional( String emailAddress) throws Exception;

	public Map<String, List<Object>> getSkuForTradePriceForProfessionalNew( String emailAddress, String emailaddress) throws Exception;

	public String getTiffanyColorPlusSKU() throws Exception;

	public List<String> getSpecialDiscountSKU( String emailAddress) throws Exception;

	public Map<String, List<Object>> getClearanceSaveOverUnderDiffSku() throws Exception;

	public Map<String, List<Object>> getMultiProductSkuDetails() throws Exception;

	public String getShipsFreeToStatesWithStores() throws Exception;

	public Map<String, String> getPopularColorsSKU() throws Exception;

	public String getCallCenterSku() throws Exception;

	public String getNoReviewProductSku() throws Exception;

	public String getEnergyInfoSku() throws Exception;

	public Map<String, String> getShortSKUAndStatus() throws Exception;

	public String getSchonbekProductOptionSku() throws Exception;

	public String getCustomerReviewSku() throws Exception;

	public Map<String, List<Object>> getBYOTrackLightItemCase2() throws Exception;

	public Map<String, List<Object>> getBYPDimmerItemDetails() throws Exception;

	public Map<String, List<Object>> getHousingOptionsSKUs() throws Exception;

	public Map<String, List<Object>> getBYOTrackLightItemCase1() throws Exception;

	public String getColorablePatternSKU() throws Exception;

	public String getProductWithKioskStyleProductSku() throws Exception;

	public Map<String, String> getSourceAndRecipientOfEmail( String email) throws Exception;

	public String getSKUWithVideoAssociated() throws Exception;

	public String getSchonbekProductSKU() throws Exception;

	public Map<String, List<Object>> getNonComboSKU() throws Exception;

	public String getProductWithKioskModeSku() throws Exception;

	public String getDailySaleAndLeftItemShortSku() throws Exception;

	public Map<String, List<Object>> getComparePriceSku() throws Exception;

	public List<String> GetESIUserOrderDetails() throws Exception;

	public String getStoreOrderID() throws Exception;

	public String getPartialOrderID() throws Exception;

	public Map<String, List<Object>> getOrderIdWithAllPossibleItemOrderStatus() throws Exception;

	public Map<String,String> getNewAccountDetails(String  email) throws Exception;

	public boolean getIsApprovedColumnForNewAccount(String emailAddress) throws Exception;

	public String getOrderIDExceptCancelledOrder() throws Exception;

	public Map<String, String> getOrderDetailsInfo() throws Exception;

	public Map<String, String> getUserContactInfoByEmail( String emailId) throws Exception;

	public String getProductWithTrim() throws Exception;

	public Map<String,List<Object>> getOptOutEmailAddressaFromUserProfile(String email) throws Exception;

	public List<String> getIsApprovedForNewAccount(String emailAddress) throws Exception;

	public String getSubscribeEmailAddress() throws Exception;

	public String getSellableRandomSingleSKU() throws Exception;

	public String getSellableRandomComboSKU() throws Exception;

	public Map<String, String> getSimilarDesignSkuForExternalReferrer() throws Exception;

	public String getSkuHavingShippingCharge() throws Exception;

	public String getShopByRoomPageFilterURL() throws Exception;

	public Map<String,List<Object>> getFreightChargesForSkuAndServiceLevel(String sku) throws Exception;

	public String getSkuForExpeditedProcessing() throws Exception;

	public String getRandomSellableSkuPriceLessThanFive() throws Exception;

	public String getZipCodeFromStoreList() throws Exception;

	public Map<String, String> getProductAndFinalizePageURL() throws Exception;

	public List<String> getShippingChargesForAlasksForSku( String sku) throws Exception;

	public String getRandomSellableSkuWithoutSpecialOrder() throws Exception;

	public String getRandomNonUMRPsku() throws Exception;

	public List<String> getTwoSkusForAR() throws Exception;

	public String GetNonColorPlusComboSku() throws Exception;

	public String getSkuForAR() throws Exception;

	public String getRandomSkuForPriceBetweenTenToTwenty() throws Exception;

	public String getManualDiscount(String orderID) throws Exception;

	public String GetRandomSellableSkuPriceLessThanTwoHundred() throws Exception;

	public String getOrderIDInDOMExportOrderHeader( String orderId) throws Exception;

	public Map<String, String> getNPCSINewShippingAddress( String emailId) throws Exception;

	public String getRandomSellableSkuPriceInBetweenTenAndTwentyFive() throws Exception;

	public String getRandomSellableSkuWithoutSpecialOrder(String orderID) throws Exception;

	public String getComboSkuWithPriceBetweenTenAndTwentyFive() throws Exception;

	public void validatePromoCode( String promocode) throws Exception;

	public String GetNonColorPlusSkuCantShipToStore() throws Exception;

	public String getRandomSellableSkuPriceGreaterThanTwoHundred() throws Exception;

	public Map<String, String> getOrderIDInGlobalOrderHeader(String orderID) throws Exception;

	public String getSKUOfWhiteGloveProduct() throws Exception;

	public Map<String, String> getWhiteGloveProductDetails( String orderID) throws Exception;

	public String getRandomSellableSkuPriceBelowTwentyFive() throws Exception;

	public String getComboSkuForL110() throws Exception;

	public String getRandomSellableSkuPriceBetweenTenAndTwenty() throws Exception;

	public Map<String, String> getOrderDetailsOfUser( String emailId) throws Exception;

	public String getOrderIDInTblorderheader( String orderID) throws Exception;

	public List<String> getHoldReasonDescriptionForOrderID( String orderID) throws Exception;

	public String getAuthorNameFromGlobalWishListTable(String wishListName) throws Exception;

	public String getAuthorNameFromGlobalCartTable(String cartNumber) throws Exception;

	public Map<String, String> getOrderDetailsInGlobalOrderHeader( String orderId) throws Exception;

	public Map<String, String> getOrderDetailsInDOMExportOrderHeader( String orderId) throws Exception;

	public Map<String, List<Object>> getTopNSellableRandomSKUBetweenTwentyAndFifty( int count) throws Exception;

	public List<Object> getTopNSellableRandomSKUWithoutRelatedProduct( int count) throws Exception;

	public Map<String, String> getProductInventory( String randomSKU) throws Exception;

	public Map<String, String> getCommissionEmployeeNumberAssociatedWithUserProfile( ) throws Exception;

	public Map<String, String> getEmployeeIDsAssociatedWithOrder(String orderId) throws Exception;

	public String getSkuWithoutThresholdDeliveryOption() throws Exception;

	public String getSkuForExpeditedOption() throws Exception;

	public String getSingleSKU() throws Exception;

	public String getProductSku() throws Exception;

	public String getPriceOfAvailableOptionSKU( String sku) throws Exception;

	public String getSkuForCheckStoreAvailabilityLink() throws Exception;
	
	public String getSkuForCustomerReview() throws Exception;

	public String getProductDetailsFromSku() throws Exception;

	public Map<String, String> getWareHouseInventory() throws Exception;

	public String getSkuForPLAFunctionality() throws Exception;

}
