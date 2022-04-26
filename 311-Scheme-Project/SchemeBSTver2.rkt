#lang scheme
;Greg Vincent
;Binary Search Tree in Scheme

;Return an empty BST
(define emptyBST
  (lambda()
    '()))

;Create a BST with a singular node
(define (singleEBST node) (cons node null null))

;Create a BST with left, right and element
(define (createfullBST element left right) (cons element left right)) 
  
;Return the root node of the BST
(define (rootNode BST)(car BST))

;BST empty function 
(define emptyTree? (lambda(BST) (null? BST)))

;Return the left subtree
(define (leftSubTree BST) (car (cdr BST)))
  
;Return right subtree
;first two cdrs truncate the list. car returns that value
(define (rightSubTree BST) (car (cdr (cdr BST))))

;postOrder traversal of the list
(define (postOrder BST)
  (if (emptyTree? BST)) '()
      (postOrder(leftSubTree BST))
      (postOrder(rightSubTree BST))
      (cons(rootNode BST)))


;preOrder traversal of a list
(define (preOrder BST)
  (if (emptyTree? BST)) '()
      (cons(rootNode BST )))
      (preOrder(leftSubTree BST))
      (preOrder(rightSubTree BST)
 
;inOrder traversal of a list
(define (inOrder BST)
  (if (emptyTree? BST)) '()
       (inOrder(leftSubTree BST)
       (cons(rootNode BST)
       (inOrder(rightSubTree BST)



 (define insert
   (lambda (BST insertVal)
     (cond
       ((emptyTree? BST)
        (createfullBST insertVal (emptyBST) (emptyBST)))
       ((< insertVal (rootNode BST)
           

  
