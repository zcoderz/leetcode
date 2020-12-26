package frequent.medium;

public class ProductArrayExceptSelf {

    /**
     * the questions asks to calculate product of array except the given index but without using that index
     *
     * concept is simple but tricky. product is essentially product of items on left and that on the right
     * so you calculate in outer array product of items left of the index
     * and then start from right , tracking product in right and multiplying by that on left to get the answer
     *
     * the question requires to create a new array to return the product of array except self and no more memory
     * except a variable to track a right count
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int [] out = new int[nums.length];
        int product = 1;
        for (int i =0; i < nums.length; i++) {
            out[i] = product;
            product *= nums[i];
        }
        int rightProduct = 1;
        for (int i =out.length-1; i > -1; i--) {
            out[i] = out[i] * rightProduct;
            rightProduct = nums[i] * rightProduct;
        }
        return out;
    }
}
