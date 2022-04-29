
;Greg Vincent
;Binary Search Tree in Scheme

;Return an empty BST
(define emptyBST '())

;Create a BST with a singular node
(define (singleEBST node) (cons node null null))

;Create a BST with left, right and element
(define (createfullBST element left right) (cons element (list left right)))  
  
;Return the root node of the BST
(define (rootNode BST) (car BST))

;BST empty function 
(define emptyTree? (lambda(BST) (null? BST)))

;Return the left subtree
(define (leftSubTree BST) (car (cdr BST)))
  
;Return right subtree
;first two cdrs truncate the list. car returns that value
(define (rightSubTree BST) (car (cdr (cdr BST))))

;postOrder traversal of the list
(define (postOrder BST)
  (if (emptyTree? BST)
     '()
      (list (postOrder(leftSubTree BST)) (postOrder(rightSubTree BST))
      (rootNode BST))))
 

;preOrder traversal of a list
(define (preOrder BST)
  (if (emptyTree? BST)
     '()
      (list (rootNode BST) (preOrder(leftSubTree BST))
      (preOrder(rightSubTree BST)))))
 
;inOrder traversal of a list
(define (inOrder BST)
 (if (emptyTree? BST)
      '()
       (list (inOrder(leftSubTree BST)) (rootNode BST)
       (inOrder(rightSubTree BST)))))

;list insertion
 (define insert 
   (lambda (BST insertVal)
     (if
       ;check if the value and root node are the same(no duplicates)
       (emptyTree? BST)
        ;create a new tree 
        (createfullBST insertVal emptyBST emptyBST)
        (if (= insertVal (rootNode BST)) BST
       ;check for precedence between inserted node and rootNode
       (if (< insertVal (rootNode BST))
           ;if insertVal < rootNode -> create a new subtree
           (createfullBST (rootNode BST)
                          (insert (leftSubTree BST) insertVal)
                          (rightSubTree BST))
           ;if insertVal > rootNode -> create a new subtree
            (createfullBST (rootNode BST)
                           (leftSubTree BST)
                           (insert (rightSubTree BST) insertVal)))
        )))) 

;search for a value in a BST
(define (search BST searchVal)
  ;check if the tree is empty
  (if (emptyTree? BST)
      #f
      ;if the root is equal to the search value return
      (if (= (rootNode BST) searchVal)
          #t
          ;if the value is less than the root
          (if (< searchVal (rootNode BST))
              ;go left
              (search (leftSubTree BST) searchVal)
              ;else go right
              (search (rightSubTree BST) searchVal)))))


(define tree '(30 (20 () ()) (50 () ())))
(define tree1 '(10 (5 () ()) (21 () ())))
(search tree1 10)
(define tree1 (insert tree1 40))
