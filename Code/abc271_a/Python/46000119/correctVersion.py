a = int(input())
b = format(a,"x")
b = str(b)

if a < 16:
  b = "0"+ b
  
print(b.upper())