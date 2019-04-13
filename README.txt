If a SKU is provided that isn't in the store then log and ignore it; we are assuming that we should 
complete the total generation, it's just that somehow something would have gotten in the cart that 
the store doesn't know about.

CheckoutController only returns a total; it doesn't give any indication on how that total was calculated.