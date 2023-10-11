info = list(map(int, input().split()))
n_rows = info[0]
n_columns = info[1]

matrix = []
for i in range(n_rows):
	matrix.append(list(map(int, input().split())))

does_satisfy = True

for i in range(n_rows):
	for j in range(n_rows):
		if i < j:
			for l in range(n_columns):
				for k in range(n_columns):
					if l < k:
						if matrix[i][l] + matrix[j][k] > matrix[j][l] + matrix[i][k]:
							does_satisfy = False
							# print(matrix[i][l], matrix[j][k])
							# print(matrix[j][l], matrix[i][k])
if does_satisfy:
	print('Yes')
else:
	print('No')