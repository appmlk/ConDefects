def base10int(value, base):
  if int(value / base): return base10int(int(value / base), base) + str(value % base)
  return str(value % base)
def dbl(x): return str(2*int(x))
print("".join(list(map(dbl, base10int(int(input()), 5)))))